package ua.my.ukrnetdump.core;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.my.ukrnetdump.core.model.DataModel;
import ua.my.ukrnetdump.core.utill.CssClass;
import ua.my.ukrnetdump.core.utill.MyUtill;

import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import ua.my.ukrnetdump.core.net.UkrNetConnection;
import ua.my.ukrnetdump.core.utill.Consts;
import static ua.my.ukrnetdump.core.utill.MyUtill.cleanStringBuilder;

public class Parse {

    private List<DataModel> list;
    final private Consts consts;
    final private static Logger LOGGER = Logger.getLogger(Parse.class.getName());

    
    public Parse(Document html,Consts c) {
        LOGGER.setLevel(Level.ALL);
        consts = c;
        try {
            Handler logHandText = new FileHandler(consts.getFILE_LOG_PARSER(),true);
            Handler logHandXml = new FileHandler(consts.getFILE_LOG_PARSER()+".xml");

            LOGGER.addHandler(logHandText);
            LOGGER.addHandler(logHandXml);

            logHandText.setFormatter(new SimpleFormatter());
            logHandXml.setFormatter(new XMLFormatter());

            generalParse(html);

            logHandText.close();
            logHandXml.close();

        } catch (IOException | SecurityException ex) {
            System.err.println(ex.getMessage());
        }


    }

    private void generalParse(Document html) {
        Element generalElement = html.select(CssClass.GENRAL).first();

        if (generalElement==null){
            LOGGER.log(Level.SEVERE,"generalElement is NULL");
            return;
        }

        Elements generalElementsList = generalElement.getElementsByClass(CssClass.BLOCKS_TAG_LIST);

        if (generalElementsList==null){
            LOGGER.log(Level.SEVERE,"generalElementList is NULL");
            return;
        }


        list = new ArrayList<>(80);
        
        StringBuilder tagName = new StringBuilder();
        StringBuilder titleNews = new StringBuilder();
        StringBuilder titleNewsUrl = new StringBuilder();
        StringBuilder dateNews = new StringBuilder();
        StringBuilder textNews = new StringBuilder();
        
        for (Element childElement : generalElementsList){
            tagName.append(childElement.select(CssClass.TAGNAME).text());
            Elements childGeneralElementsList = childElement.getElementsByClass(CssClass.TILTE_LIST);
                for (Element child : childGeneralElementsList) {
                    titleNews.append(child.select(CssClass.TILTENEWS).text());
                    titleNewsUrl.append(child.select(CssClass.TILTENEWS).select(CssClass.LINK).attr(CssClass.LINK_HREF));
                    dateNews.append(MyUtill.getDateNow()).append(" ").append(child.select(CssClass.DATENEWS).text());
                    getNewsText(titleNewsUrl, textNews);
                    list.add(new DataModel(tagName.toString(), titleNews.toString(), titleNewsUrl.toString(), dateNews.toString(), textNews.toString()));
                
                    cleanStringBuilder(titleNews);
                    cleanStringBuilder(titleNewsUrl);
                    cleanStringBuilder(dateNews);
                    cleanStringBuilder(textNews);
                }    
                cleanStringBuilder(tagName);
        }
    }
    
    private void  getNewsText(StringBuilder url,StringBuilder textNews){
        
        String tempUrl = fixUrl(url);
        if (tempUrl!=null){
            UkrNetConnection connect;
            try {
                 connect  = new UkrNetConnection(consts, tempUrl);
                if (connect.getHtml() == null) {
                    textNews.append("Cannot connect");
                    LOGGER.log(Level.INFO, "Erroe msg=Cannot connect Url={0}", url);
                    return;
                }
            }catch (UncheckedIOException e){
                LOGGER.log(Level.SEVERE, "Fatal erorr ", e);
                return;
            }
            Element general = connect.getHtml().select(CssClass.NEWSTEXT).first();
            if (general==null){
                textNews.append("Error");
                LOGGER.log(Level.INFO,"Erroe msg=general element is null Url={0}",url);
                return;
            }
            textNews.append(general.text());
            general = connect.getHtml().select(CssClass.NEWSTEXT_URL).first();
            cleanStringBuilder(url);
            url.append(general.select(CssClass.LINK).attr(CssClass.LINK_HREF));
        }else {
            textNews.append("Error url");
            LOGGER.log(Level.INFO,"Erroe msg=errer url Url={0}",url);
        }
    }
    
    private String fixUrl(StringBuilder url){
       
        if (!url.toString().contains("ukr.net")){
            return null;
        }   
        if (url.toString().contains("http"))
            return url.toString();         
            
        return "http:"+url.toString();
        //else if 
    }
    
    public List<DataModel> getList() {
        return list;
    }
}

package ua.my.ukrnetdump.core;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.my.ukrnetdump.core.model.DataModel;
import ua.my.ukrnetdump.core.utill.CssClass;
import ua.my.ukrnetdump.core.utill.MyUtill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel-pc on 17.07.15.
 */
public class Parse {

    private List<DataModel> list;

    public Parse(Document html) {
        generalParse(html);
    }

    private void generalParse(Document html) {

        Element generalElement = html.select(CssClass.GENRAL).first();

        if (generalElement==null)
            return;

        Elements generalElementsList = generalElement.getElementsByClass(CssClass.BLOCKS_TAG_LIST);

        if (generalElementsList==null)
            return;

        list = new ArrayList<>(100);

        String tagName;
        String titleNews;
        String titleNewsUrl;
        String dateNews;

        for (Element childElement : generalElementsList){
           System.out.println(childElement.select(CssClass.TAGNAME).text());
            tagName = childElement.select(CssClass.TAGNAME).text();
            Elements childGeneralElementsList = childElement.getElementsByClass(CssClass.TILTE_LIST);
                for (Element child : childGeneralElementsList) {
                    System.out.println(child.select(CssClass.TILTENEWS).text());
                    titleNews = child.select(CssClass.TILTENEWS).text();
                    titleNewsUrl = child.select(CssClass.TILTENEWS).text();
                    dateNews = MyUtill.getDateNow() + child.select(CssClass.DATENEWS).text();
                    list.add(new DataModel(tagName, titleNews, null, null, null));
                }
            }
        }
}

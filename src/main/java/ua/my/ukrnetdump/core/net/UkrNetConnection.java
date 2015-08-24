package ua.my.ukrnetdump.core.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ua.my.ukrnetdump.core.utill.Consts;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Created by pavel-pc on 16.07.15.
 */
public class UkrNetConnection {

    private Document html;
    private String url;    
    public UkrNetConnection(Consts cosnts) {
        connect(cosnts);
    }
    public UkrNetConnection(Consts cosnts,String url) {
        this.url = url;
        connect(cosnts);
    }
    private void connect(Consts consts) {
        try {
            if (url!=null)
                html = Jsoup.connect(url).userAgent(consts.getUSERAGENT()).timeout(consts.getTIMEOUT()).get();
            else 
                html = Jsoup.connect(consts.getUKRNET_URL()).userAgent(consts.getUSERAGENT()).timeout(consts.getTIMEOUT()).get();
        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }
    public Document getHtml() {
        return html;
    }
}

package ua.my.ukrnetdump.core.net;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ua.my.ukrnetdump.core.utill.Consts;

import java.io.IOException;

/**
 * Created by pavel-pc on 16.07.15.
 */
public class UkrNetConnection {

    private Document html;

    public UkrNetConnection(Consts cosnts) {
        connect(cosnts);
    }


    private void connect(Consts consts){
        try {
            html = Jsoup.connect(consts.getUKRNET_URL()).userAgent(consts.getUSERAGENT()).timeout(consts.getTIMEOUT()).get();
        } catch (IOException e) {
            System.err.println("Cannot connect");
        }
    }



    public Document getHtml() {
        return html;
    }
}

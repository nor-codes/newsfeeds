package com.learn.newsfeed.service;
import net.sf.saxon.s9api.*;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

public class ParserService {


    public static void rrsNewsParser(){
        try {
            // Load file from resources/rrs/rss.xml
            InputStream inputStream = ParserService.class.getClassLoader().getResourceAsStream("rrs/rrs.xml");

            if (inputStream == null) {
                System.err.println("File not found in resources/rrs/rss.xml");
                return;
            }

            Processor processor = new Processor(false);
            DocumentBuilder builder = processor.newDocumentBuilder();
            XdmNode doc = builder.build(new StreamSource(inputStream));

            XPathCompiler xpath = processor.newXPathCompiler();

            // Extract channel title
            XdmValue channelTitle = xpath.evaluate("/rss/channel/title", doc);
            System.out.println("Channel Title: " + channelTitle.itemAt(0).getStringValue());

            // Extract each <item> title, description, and link
            for (XdmItem xdmItem : xpath.evaluate("/rss/channel/item", doc)) {
                XdmNode item = (XdmNode) xdmItem;
                String title = xpath.evaluate("title", item).itemAt(0).getStringValue();
                String description = xpath.evaluate("description", item).itemAt(0).getStringValue();
                String link = xpath.evaluate("link", item).itemAt(0).getStringValue();
                System.out.println("\n📰 News: " + title);
                System.out.println("📄 Description: " + description);
                System.out.println("🔗 Link: " + link);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

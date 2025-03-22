package com.learn.newsfeed.utils;

import com.learn.newsfeed.logging.EventLog;
import net.sf.saxon.s9api.*;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class RssProccessor {
    private final String MODULE = RssProccessor.class.getCanonicalName();
    private final String METADATA_TITLE_XPATH = "/rss/channel/title";
    private final String NEWS_ITEM_XPATH  = "/rss/channel/item";
    private final String METADATA_DESCRIPTION_XPATH  = "/rss/channel/description";
    private final String METADATA_SOURCE_LINK_XPATH  = "/rss/channel/link";
    private final String METADATA_TIMESTAMP_XPATH  = "/rss/channel/lastBuildDate";
    private final String METADATA_COPYRIGHT_XPATH = "/rss/channel/copyright";

    private void extract(byte[] fileBytes){
        try {

            InputStream inputStream = new ByteArrayInputStream(fileBytes);

            Processor processor = new Processor(false);
            DocumentBuilder builder = processor.newDocumentBuilder();
            XdmNode doc = builder.build(new StreamSource(inputStream));

            XPathCompiler xpath = processor.newXPathCompiler();


            XdmValue metadataTitle = xpath.evaluate(METADATA_TITLE_XPATH, doc);
            XdmValue metadataDescription = xpath.evaluate(METADATA_DESCRIPTION_XPATH, doc);
            XdmValue metadataSource = xpath.evaluate(METADATA_SOURCE_LINK_XPATH, doc);
            XdmValue metadataTimestamp = xpath.evaluate(METADATA_TIMESTAMP_XPATH, doc);
            XdmValue metadataCopyRight = xpath.evaluate(METADATA_COPYRIGHT_XPATH, doc);


            for (XdmItem xdmItem : xpath.evaluate(NEWS_ITEM_XPATH, doc)) {
                XdmNode item = (XdmNode) xdmItem;
                String title = xpath.evaluate("title", item).itemAt(0).getStringValue();
                String description = xpath.evaluate("description", item).itemAt(0).getStringValue();
                String link = xpath.evaluate("link", item).itemAt(0).getStringValue();
                String publicationDate = xpath.evaluate("pubDate", item).itemAt(0).getStringValue();
                XdmValue thumbnailNode = xpath.evaluate("media:thumbnail/@url", item);
                String thumbnailUrl = !thumbnailNode.isEmpty() ? thumbnailNode.itemAt(0).getStringValue() : "No Thumbnail";

                System.out.println("\n📰 News: " + title);
                System.out.println("📄 Description: " + description);
                System.out.println("🔗 Link: " + link);
                System.out.println("🔗 publicationDate: " + publicationDate);
                System.out.println("🔗 Thumbnail: " + thumbnailUrl);
            }

        } catch (Exception e) {
            EventLog.module(MODULE)
                    .message(e.getMessage())
                    .error()
                    .log();
        }
    }
}

package com.maciejnowicki.bloxdownloader.data.parsers;

import com.maciejnowicki.bloxdownloader.data.BloxEntry;
import com.maciejnowicki.bloxdownloader.data.BloxImage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class BloxEntryParser {

    private final static String CLASS_ENTRY = "BlogWpisBox";
    private final static String CLASS_ENTRY_TITLE = "BlogWpisItemTytul";
    private final static String CLASS_ENTRY_CONTENT = "BlogWpisTresc";
    private final static String CLASS_ENTRY_TAGS = "infoTagi";
    private final static String CLASS_ENTRY_DATE = "BlogDataWpisu";
    private final static String CLASS_ENTRY_CATEGORY = "IPTkategoria";

    public List<BloxEntry> parseEntries(Document site) {
        List<BloxEntry> entries = new ArrayList<>();

        Elements elements = site.getElementsByClass(CLASS_ENTRY);
        for (Element e : elements) {
            BloxEntry entry = BloxEntry.Builder.aBloxEntry()
                    .withTitle(parseTitle(e))
                    .withContent(parseContent(e))
                    .withTags(parseTags(e))
                    .withDate(parseDate(e))
                    .withCategory(parseCategory(e))
                    .withImages(parseImages(e))
                    .build();

            entries.add(entry);
        }

        return entries;
    }

    private String parseTitle(Element e) {
        return e.getElementsByClass(CLASS_ENTRY_TITLE).first().text();
    }

    private String parseContent(Element e) {
        return e.getElementsByClass(CLASS_ENTRY_CONTENT).first().html();
    }

    private List<String> parseTags(Element e) {
        List<String> tags = new ArrayList<>();
        if (e.getElementsByClass(CLASS_ENTRY_TAGS).first() != null) {
            Elements tagElements = e.getElementsByClass(CLASS_ENTRY_TAGS)
                    .first()
                    .getElementsByTag("a");
            for (Element tag : tagElements) {
                tags.add(tag.text());
            }
        }

        return tags;
    }

    private String parseDate(Element e) {
        if (e.previousElementSibling().hasClass(CLASS_ENTRY_DATE)) {
            return e.previousElementSibling().text().split(",")[1].trim();
        }

        return "";
    }

    private String parseCategory(Element e) {
        if (e.getElementsByClass(CLASS_ENTRY_CATEGORY).first() != null) {
            return e.getElementsByClass(CLASS_ENTRY_CATEGORY).first().text();
        }

        return "";
    }

    private List<BloxImage> parseImages(Element e) {
        List<BloxImage> images = new ArrayList<>();
        for (Element img : e.getElementsByTag("img")) {
            BloxImage bloxImage = BloxImage.Builder.aBloxImage()
                            .withTitle(img.attr("title"))
                            .withAlt(img.attr("alt"))
                            .withUrl(img.attr("src"))
                            .build();
            images.add(bloxImage);
        }

        return images;
    }
}

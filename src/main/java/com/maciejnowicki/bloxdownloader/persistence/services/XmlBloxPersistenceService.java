package com.maciejnowicki.bloxdownloader.persistence.services;

import com.maciejnowicki.bloxdownloader.data.BloxDownloader;
import com.maciejnowicki.bloxdownloader.data.BloxEntry;
import com.maciejnowicki.bloxdownloader.data.BloxImage;
import com.maciejnowicki.bloxdownloader.data.FileFormat;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class XmlBloxPersistenceService implements BloxPersistenceService {
    @Override
    public void save(BloxDownloader bloxDownloader, File file) throws IOException {
        List<BloxEntry> entries = bloxDownloader.getEntries();
        Document xml = DocumentHelper.createDocument();
        Element root = xml.addElement("bloxDownloader");

        for (BloxEntry entry : entries) {
            Element xmlEntry = root.addElement("bloxDownloader-entry");
            xmlEntry.addElement("title")
                    .addText(entry.getTitle());

            xmlEntry.addElement("date")
                    .addText(entry.getDate());

            Element xmlTags = xmlEntry.addElement("tags");
            for (String tag : entry.getTags()) {
                xmlTags.addElement("tag")
                        .addText(tag);
            }

            xmlEntry.addElement("content")
                    .addText(entry.getContent());

            xmlEntry.addElement("category")
                    .addText(entry.getCategory());

            Element xmlImages = xmlEntry.addElement("images");
            for (BloxImage img : entry.getImages()) {
                Element xmlImg = xmlImages.addElement("bloxDownloader-image");

                xmlImg.addElement("title")
                        .addText(img.getTitle());

                xmlImg.addElement("alt")
                        .addText(img.getAlt());

                xmlImg.addElement("content")
                        .addText(Base64.getEncoder().encodeToString(img.download()));
            }
        }

        XMLWriter xmlWriter = new XMLWriter(new FileWriter(file));
        xmlWriter.write(xml);
        xmlWriter.close();
    }

    @Override
    public BloxDownloader load(File file) throws IOException {
        return null;
    }

    @Override
    public FileFormat getFileFormat() {
        return FileFormat.XML;
    }
}

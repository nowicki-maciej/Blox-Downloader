package com.maciejnowicki.bloxdownloader.persistence.services;

import com.maciejnowicki.bloxdownloader.blox.Blox;
import com.maciejnowicki.bloxdownloader.blox.entry.BloxEntry;
import com.maciejnowicki.bloxdownloader.blox.entry.BloxImage;
import com.maciejnowicki.bloxdownloader.persistence.FileFormat;
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
    public void save(Blox blox, File file) throws IOException {
        List<BloxEntry> entries = blox.getEntries();
        Document xml = DocumentHelper.createDocument();
        Element root = xml.addElement("blox");

        for (BloxEntry entry : entries) {
            Element xmlEntry = root.addElement("blox-entry");
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
                Element xmlImg = xmlImages.addElement("blox-image");

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
    public Blox load(File file) throws IOException {
        return null;
    }

    @Override
    public FileFormat getFileFormat() {
        return FileFormat.XML;
    }
}

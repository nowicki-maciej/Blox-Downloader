package com.maciejnowicki.bloxdownloader;

import com.maciejnowicki.bloxdownloader.data.BloxDownloader;
import com.maciejnowicki.bloxdownloader.data.FileFormat;

import java.io.File;

public class Runner {
    public static void main(String[] args) {
        BloxDownloader.fromName("agnieszkagil")
                .save(new File("blox.xml"), FileFormat.XML);
    }
}

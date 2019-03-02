package com.maciejnowicki.bloxdownloader.persistence.services;

import com.google.gson.Gson;
import com.maciejnowicki.bloxdownloader.data.BloxDownloader;
import com.maciejnowicki.bloxdownloader.data.Blox;
import com.maciejnowicki.bloxdownloader.data.repository.MemoryBloxRepository;
import com.maciejnowicki.bloxdownloader.data.FileFormat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class JsonBloxPersistenceService implements BloxPersistenceService {

    private Gson gson = new Gson();

    @Override
    public void save(BloxDownloader bloxDownloader, File file) throws IOException {
        //TODO: change to Jackson
        String serialized = gson.toJson(
                new Blox(bloxDownloader.getName(), bloxDownloader.getEntries())
        );

        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(serialized);
        }
    }

    @Override
    public BloxDownloader load(File file) throws IOException {
        Blox blox = gson.fromJson(
                new String(Files.readAllBytes(file.toPath())),
                Blox.class
        );

        return new BloxDownloader(new MemoryBloxRepository(blox));
    }

    @Override
    public FileFormat getFileFormat() {
        return FileFormat.JSON;
    }
}

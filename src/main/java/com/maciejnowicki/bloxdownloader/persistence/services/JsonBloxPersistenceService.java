package com.maciejnowicki.bloxdownloader.persistence.services;

import com.google.gson.Gson;
import com.maciejnowicki.bloxdownloader.blox.Blox;
import com.maciejnowicki.bloxdownloader.blox.repository.BloxDTO;
import com.maciejnowicki.bloxdownloader.blox.repository.MemoryBloxRepository;
import com.maciejnowicki.bloxdownloader.persistence.FileFormat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class JsonBloxPersistenceService implements BloxPersistenceService {

    private Gson gson = new Gson();

    @Override
    public void save(Blox blox, File file) throws IOException {
        //TODO: change to Jackson
        String serialized = gson.toJson(
                new BloxDTO(blox.getName(), blox.getEntries())
        );

        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(serialized);
        }
    }

    @Override
    public Blox load(File file) throws IOException {
        BloxDTO bloxDTO = gson.fromJson(
                new String(Files.readAllBytes(file.toPath())),
                BloxDTO.class
        );

        return new Blox(new MemoryBloxRepository(bloxDTO));
    }

    @Override
    public FileFormat getFileFormat() {
        return FileFormat.JSON;
    }
}

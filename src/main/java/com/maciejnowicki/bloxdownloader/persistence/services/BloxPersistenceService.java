package com.maciejnowicki.bloxdownloader.persistence.services;

import com.maciejnowicki.bloxdownloader.blox.Blox;
import com.maciejnowicki.bloxdownloader.persistence.FileFormat;

import java.io.File;
import java.io.IOException;

public interface BloxPersistenceService {

    void save(Blox blox, File file) throws IOException;
    Blox load(File file) throws IOException;
    FileFormat getFileFormat();
}

package com.maciejnowicki.bloxdownloader.persistence.services;

import com.maciejnowicki.bloxdownloader.data.BloxDownloader;
import com.maciejnowicki.bloxdownloader.data.FileFormat;

import java.io.File;
import java.io.IOException;

public interface BloxPersistenceService {

    void save(BloxDownloader bloxDownloader, File file) throws IOException;
    BloxDownloader load(File file) throws IOException;
    FileFormat getFileFormat();
}

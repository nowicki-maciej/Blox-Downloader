package com.maciejnowicki.bloxdownloader.persistence;

import com.maciejnowicki.bloxdownloader.data.FileFormat;
import com.maciejnowicki.bloxdownloader.persistence.services.BloxPersistenceService;
import com.maciejnowicki.bloxdownloader.persistence.services.JsonBloxPersistenceService;
import com.maciejnowicki.bloxdownloader.persistence.services.XmlBloxPersistenceService;

import java.util.HashMap;
import java.util.Map;

public class PersistenceServiceProvider {

    private static final Map<FileFormat, BloxPersistenceService> persistenceServiceRegistry;

    static {
        persistenceServiceRegistry = new HashMap<>();
        persistenceServiceRegistry.put(FileFormat.JSON, new JsonBloxPersistenceService());
        persistenceServiceRegistry.put(FileFormat.XML, new XmlBloxPersistenceService());
//        persistenceServiceRegistry.put(FileFormat.JSON, new ZipBloxPersistenceService());
    }

    public static BloxPersistenceService get(FileFormat format) {
        if (!persistenceServiceRegistry.containsKey(format)) {
            throw new RuntimeException("Unsupported file format.");
        }

        return persistenceServiceRegistry.get(format);
    }
}

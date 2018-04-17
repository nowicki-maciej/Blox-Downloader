package com.maciejnowicki.bloxdownloader.persistence;

import com.maciejnowicki.bloxdownloader.persistence.services.BloxPersistenceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class BloxPersistenceServiceProvider {

    private Map<FileFormat, BloxPersistenceService> serviceRegistry = new HashMap<>();

    public BloxPersistenceServiceProvider(List<BloxPersistenceService> services) {
        services.forEach(s -> serviceRegistry.put(s.getFileFormat(), s));
    }

    public BloxPersistenceService getServiceFor(FileFormat fileFormat) {
        BloxPersistenceService service = serviceRegistry.get(fileFormat);

        if (isNull(service)) {
            throw new RuntimeException("Unsupported file format: " + fileFormat);
        }

        return service;
    }
}

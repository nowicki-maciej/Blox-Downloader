package com.maciejnowicki.bloxdownloader.blox;

import com.maciejnowicki.bloxdownloader.blox.entry.BloxEntry;
import com.maciejnowicki.bloxdownloader.blox.repository.BloxRepository;
import com.maciejnowicki.bloxdownloader.blox.repository.UrlBloxRepository;
import com.maciejnowicki.bloxdownloader.persistence.BloxPersistenceServiceProvider;
import com.maciejnowicki.bloxdownloader.persistence.FileFormat;
import com.maciejnowicki.bloxdownloader.persistence.services.BloxPersistenceService;
import com.maciejnowicki.bloxdownloader.persistence.services.JsonBloxPersistenceService;
import com.maciejnowicki.bloxdownloader.persistence.services.XmlBloxPersistenceService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Blox {

    private static final BloxPersistenceServiceProvider PERSISTENCE_SERVICE_PROVIDER = new BloxPersistenceServiceProvider(
            Arrays.asList(
                    new JsonBloxPersistenceService(),
//                    new ZipBloxPersistenceService(),
                    new XmlBloxPersistenceService()
            )
    );

    private BloxRepository bloxRepository;

    public Blox(BloxRepository bloxRepository) {
        this.bloxRepository = bloxRepository;
    }

    public void save(File file, FileFormat fileFormat) {
        BloxPersistenceService service = PERSISTENCE_SERVICE_PROVIDER.getServiceFor(fileFormat);
        try {
            service.save(this, file);
        } catch (IOException e) {
            throw new RuntimeException("There was a problem saving to file.", e);
        }
    }

    public String getName() {
        return bloxRepository.getName();
    }

    public List<BloxEntry> getEntries() {
        return bloxRepository.getAllEntries();
    }

    public static Blox fromName(String name) {
        return new Blox(new UrlBloxRepository(name));
    }

    public static Blox fromUrl(String url) {
        List<String> parts = Arrays.asList(url.split("\\."));
        String name = parts.get(
                parts.indexOf("blox") - 1
        );

        return new Blox(new UrlBloxRepository(name));
    }

    public static Blox fromFile(File file, FileFormat fileFormat) {
        BloxPersistenceService service = PERSISTENCE_SERVICE_PROVIDER.getServiceFor(fileFormat);
        try {
            return service.load(file);
        } catch (IOException e) {
            throw new RuntimeException("There was a problem reading from file.", e);
        }
    }
}

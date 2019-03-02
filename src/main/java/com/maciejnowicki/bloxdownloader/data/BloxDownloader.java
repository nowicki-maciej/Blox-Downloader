package com.maciejnowicki.bloxdownloader.data;

import com.maciejnowicki.bloxdownloader.data.repository.BloxRepository;
import com.maciejnowicki.bloxdownloader.data.repository.UrlBloxRepository;

import java.util.Arrays;
import java.util.List;

public class BloxDownloader {

	private static final String BLOX_URL_TEMPLATE = "http://%s.blox.pl";

	private BloxRepository bloxRepository;
	private Blox blox;

//    public BloxDownloader(BloxRepository bloxRepository) {
//        this.bloxRepository = bloxRepository;
//    }

//    public void save(File file, FileFormat fileFormat) {
//        BloxPersistenceService service = PersistenceServiceProvider.get(fileFormat);
//        try {
//            service.save(this, file);
//        } catch (IOException e) {
//            throw new RuntimeException("There was a problem saving to file.", e);
//        }
//    }
//
//    public String getName() {
//        return bloxRepository.getName();
//    }
//
//    public List<BloxEntry> getEntries() {
//        return bloxRepository.getAllEntries();
//    }

	public static BloxProxy fromName(String name) {
		return new BloxProxy(
			name,
			new UrlBloxRepository(String.format(BLOX_URL_TEMPLATE, name))
		);
	}
}

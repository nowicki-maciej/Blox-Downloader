package com.maciejnowicki.bloxdownloader.data;

import com.maciejnowicki.bloxdownloader.data.repository.BloxRepository;

public class BloxProxy {

	private String name;
	private BloxRepository repository;

	public BloxProxy(String name, BloxRepository repository) {
		this.name = name;
		this.repository = repository;
	}

	private Blox getBlox() {
		return new Blox(
			name,
			repository.getAllEntries()
		);
	}
}

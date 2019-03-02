package com.maciejnowicki.bloxdownloader.data;

import java.util.List;

public class Blox {

	private String name;
	private List<BloxEntry> entries;

	public Blox(String name, List<BloxEntry> entries) {
		this.name = name;
		this.entries = entries;
	}

	public String getName() {
		return name;
	}

	public List<BloxEntry> getEntries() {
		return entries;
	}
}

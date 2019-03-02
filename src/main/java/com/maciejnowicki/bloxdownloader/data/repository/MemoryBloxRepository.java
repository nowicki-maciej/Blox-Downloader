package com.maciejnowicki.bloxdownloader.data.repository;

import com.maciejnowicki.bloxdownloader.data.BloxEntry;
import com.maciejnowicki.bloxdownloader.data.Blox;

import java.util.List;

public class MemoryBloxRepository implements BloxRepository {

    private String name;
    private List<BloxEntry> entries;

    public MemoryBloxRepository(Blox blox) {
        this.name = blox.getName();
        this.entries = blox.getEntries();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<BloxEntry> getAllEntries() {
        return entries;
    }
}

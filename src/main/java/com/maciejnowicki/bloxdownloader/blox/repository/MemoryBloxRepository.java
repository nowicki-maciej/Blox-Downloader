package com.maciejnowicki.bloxdownloader.blox.repository;

import com.maciejnowicki.bloxdownloader.blox.entry.BloxEntry;

import java.util.List;

public class MemoryBloxRepository implements BloxRepository {

    private String name;
    private List<BloxEntry> entries;

    public MemoryBloxRepository(BloxDTO bloxDTO) {
        this.name = bloxDTO.getName();
        this.entries = bloxDTO.getBloxEntries();
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

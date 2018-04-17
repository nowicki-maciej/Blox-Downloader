package com.maciejnowicki.bloxdownloader.blox.repository;

import com.maciejnowicki.bloxdownloader.blox.entry.BloxEntry;

import java.util.List;

public class BloxDTO {

    private String name;
    private List<BloxEntry> bloxEntries;

    public BloxDTO(String name, List<BloxEntry> bloxEntries) {
        this.name = name;
        this.bloxEntries = bloxEntries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BloxEntry> getBloxEntries() {
        return bloxEntries;
    }

    public void setBloxEntries(List<BloxEntry> bloxEntries) {
        this.bloxEntries = bloxEntries;
    }
}

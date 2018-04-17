package com.maciejnowicki.bloxdownloader.blox.repository;

import com.maciejnowicki.bloxdownloader.blox.entry.BloxEntry;

import java.util.List;

public interface BloxRepository {

    String getName();
    List<BloxEntry> getAllEntries();
}

package com.maciejnowicki.bloxdownloader.data.repository;

import com.maciejnowicki.bloxdownloader.data.BloxEntry;

import java.util.List;

public interface BloxRepository {

    String getName();
    List<BloxEntry> getAllEntries();
}

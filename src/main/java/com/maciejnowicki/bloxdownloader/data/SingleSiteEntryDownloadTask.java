package com.maciejnowicki.bloxdownloader.data;

import com.maciejnowicki.bloxdownloader.data.parsers.BloxEntryParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class SingleSiteEntryDownloadTask implements Callable<List<BloxEntry>> {

    private static final Logger log = LoggerFactory.getLogger(SingleSiteEntryDownloadTask.class);

    private final String url;
    private BloxEntryParser entryParser;

    public SingleSiteEntryDownloadTask(String url) {
        this.url = url;
        entryParser = new BloxEntryParser();
    }

    @Override
    public List<BloxEntry> call() {
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.warn("Unable to reach site {}", url);
            return null;
        }

        return entryParser.parseEntries(document);
    }
}

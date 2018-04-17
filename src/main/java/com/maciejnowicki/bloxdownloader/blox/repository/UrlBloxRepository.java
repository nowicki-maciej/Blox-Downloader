package com.maciejnowicki.bloxdownloader.blox.repository;

import com.maciejnowicki.bloxdownloader.blox.entry.BloxEntry;
import com.maciejnowicki.bloxdownloader.blox.entry.SingleSiteEntryDownloadTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.Objects.isNull;

public class UrlBloxRepository implements BloxRepository {

    private static final Logger log = LoggerFactory.getLogger(UrlBloxRepository.class);
    private static final String URL_TEMPLATE = "http://%s.blox.pl";
    private static final String CLASS_PAGINATION = "BlogStronicowanieBox";
    private static final String CLASS_PAGINATION_PAGES = "BlogStronicowanieStrony";

    private String bloxName;
    private Document mainPage;

    public UrlBloxRepository(String bloxName) {
        this.bloxName = bloxName;
    }

    @Override
    public String getName() {
        return bloxName;
    }

    @Override
    public List<BloxEntry> getAllEntries() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<String> urls = fetchAllSitesUrls();
        List<Future<List<BloxEntry>>> futureEntries = new ArrayList<>();
        urls.forEach(
                url -> futureEntries.add(
                        executorService.submit(new SingleSiteEntryDownloadTask(url))
                )
        );

        List<BloxEntry> entries = new ArrayList<>();

        futureEntries.forEach(futureEntry -> {
            try {
                entries.addAll(futureEntry.get());
            } catch (InterruptedException | ExecutionException e) {
                log.warn("Fatal error: unable to finish downloading entries.");
                throw new RuntimeException("Fatal error: unable to finish downloading entries.", e);
            }
        });

        executorService.shutdown();
        return entries;
    }

    private List<String> fetchAllSitesUrls() {
        String url = getAbsoluteUrlToPage();
        Long pageCount = getPageCount();
        List<String> urls = new ArrayList<>();
        for(int i = 1; i <= pageCount; i++)
            urls.add(url + "?" + i);

        return urls;
    }

    private String getAbsoluteUrlToPage() {
        Element linkToLast = getMainPage().getElementById(CLASS_PAGINATION)
                .getElementsByClass(CLASS_PAGINATION_PAGES)
                .first()
                .getElementsByTag("a")
                .last();

        return getUrl() + linkToLast.attr("href").split("\\?")[0];
    }

    private Long getPageCount() {
        Element linkToLast = getMainPage().getElementById(CLASS_PAGINATION)
                .getElementsByClass(CLASS_PAGINATION_PAGES)
                .first()
                .getElementsByTag("a")
                .last();

        return Long.valueOf(linkToLast.text());
    }

    private String getUrl() {
        return String.format(Locale.ENGLISH, URL_TEMPLATE, bloxName);
    }

    private Document getMainPage() {
        if (isNull(mainPage)) {
            try {
                mainPage = Jsoup.connect(getUrl()).get();
            } catch (IOException e) {
                log.warn("Unable to connect to {}", getUrl());
                throw new RuntimeException("Unable to connect to " + getUrl(), e);
            }
        }

        return mainPage;
    }
}

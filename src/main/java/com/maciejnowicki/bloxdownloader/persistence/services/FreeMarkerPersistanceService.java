package com.maciejnowicki.bloxdownloader.persistence.services;

import com.maciejnowicki.bloxdownloader.data.BloxDownloader;
import com.maciejnowicki.bloxdownloader.data.FileFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerPersistanceService implements BloxPersistenceService {

    private Configuration cfg;

    public FreeMarkerPersistanceService() {
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    @Override
    public void save(BloxDownloader bloxDownloader, File file) throws IOException {
        Map<String, Object> ctx = prepareContext(bloxDownloader);
        Template template = cfg.getTemplate("bloxDownloader-template.ftl");
        try (Writer writer = new FileWriter(file)) {
            template.process(ctx, writer);
        } catch (TemplateException e) {
            throw new IOException("Failed while processing template.", e);
        }
    }

    private Map<String,Object> prepareContext(BloxDownloader bloxDownloader) {
        Map<String, Object> ctx = new HashMap<>();
        ctx.put("entries", bloxDownloader.getEntries());
        return ctx;
    }

    @Override
    public BloxDownloader load(File file) throws IOException {
        return null;
    }

    @Override
    public FileFormat getFileFormat() {
        return null;
    }
}

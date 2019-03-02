package com.maciejnowicki.bloxdownloader.data;

import java.util.ArrayList;
import java.util.List;

public class BloxEntry {
    private String title;
    private String date;
    private List<String> tags;
    private String content;
    private String category;
    private List<BloxImage> images;

    public BloxEntry() {
        tags = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public List<BloxImage> getImages() {
        return images;
    }

    public static final class Builder {
        private String title;
        private String date;
        private List<String> tags;
        private String content;
        private String category;
        private List<BloxImage> images;

        private Builder() {
        }

        public static Builder aBloxEntry() {
            return new Builder();
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public Builder withTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withImages(List<BloxImage> images) {
            this.images = images;
            return this;
        }

        public BloxEntry build() {
            BloxEntry bloxEntry = new BloxEntry();
            bloxEntry.date = this.date;
            bloxEntry.title = this.title;
            bloxEntry.images = this.images;
            bloxEntry.tags = this.tags;
            bloxEntry.category = this.category;
            bloxEntry.content = this.content;
            return bloxEntry;
        }
    }
}

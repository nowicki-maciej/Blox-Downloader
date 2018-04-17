package com.maciejnowicki.bloxdownloader.blox.entry;

import com.maciejnowicki.bloxdownloader.util.URLResourceProvider;

public class BloxImage {

    private static final URLResourceProvider URL_RESOURCE_PROVIDER = new URLResourceProvider();

    private String url;
    private String title;
    private String alt;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getAlt() {
        return alt;
    }

    public String getFilename() {
        String[] split = url.split("\\\\");
        return split[split.length - 1];
    }

    public byte[] download() {
        return URL_RESOURCE_PROVIDER.getByteArray(url);
    }

    public static final class Builder {
        private String url;
        private String title;
        private String alt;

        private Builder() {
        }

        public static Builder aBloxImage() {
            return new Builder();
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAlt(String alt) {
            this.alt = alt;
            return this;
        }

        public BloxImage build() {
            BloxImage bloxImage = new BloxImage();
            bloxImage.url = this.url;
            bloxImage.alt = this.alt;
            bloxImage.title = this.title;
            return bloxImage;
        }
    }
}

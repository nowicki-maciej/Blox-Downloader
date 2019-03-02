<?xml version="1.0" encoding="UTF-8" ?>

<bloxDownloader name="${bloxDownloader.name}">
    <#list bloxDownloader.entries as entry>
        <bloxDownloader-entry>
            <title>${entry.title}</title>
            <date>${entry.date}</date>
            <tags>
                <#list entry.tags as tag>
                    <tag>${tag}</tag>
                </#list>
            </tags>
            <content>${entry.content}</content>
            <category>${entry.category}</category>
            <images>
                <#list entry.images as img>
                    <image title="${img.title}" alt="${img.alt}">
                        <content>${img.content}</content>
                    </image>
                </#list>
            </images>
        </bloxDownloader-entry>
    </#list>
</bloxDownloader>

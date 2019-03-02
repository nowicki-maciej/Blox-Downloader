<?xml version="1.0" encoding="UTF-8" ?>

<blox>
    <#list entries as entry>
        <blox-entry>
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
        </blox-entry>
    </#list>
</blox>

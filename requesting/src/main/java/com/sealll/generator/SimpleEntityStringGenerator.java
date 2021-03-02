package com.sealll.generator;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 简单实现
 * @author sealll
 * @time 2021/1/27 20:40
 */
public class SimpleEntityStringGenerator implements EntityStringGenerator{
    @Override
    public String getParamString(String url, StringEntity entity) throws URISyntaxException, IOException {
        URI uri = new URI(url);
        String newUrl = new String(url);
        String query = uri.getQuery();
        if (query == null) {

            newUrl += "?";
        }else if(!"".equals(query) && !query.endsWith("&")){
            newUrl += "&";
        }
        String s = URLEncoder.encode(IOUtils.toString(entity.getContent(), "UTF-8"), StandardCharsets.UTF_8);
        newUrl += s;
        return newUrl;
    }
}

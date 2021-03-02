package com.sealll.http;

import com.google.gson.JsonElement;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author sealll
 * @time 2021/1/28 14:12
 */
public interface HttpRequester {
    public <T> String sendFormForText(HttpClientContext context, String url,
                                      String method, Map<String,? super String> headers,
                                      T params) throws IllegalAccessException, IOException, InvocationTargetException;
    public <T> JsonElement sendFormForJson(HttpClientContext context, String url,
                                           String method, Map<String,? super String> headers,
                                           T params) throws IllegalAccessException, IOException, InvocationTargetException;
    public <T> JsonElement sendJsonForJson(HttpClientContext context,String url,
                                           String method, Map<String,? super String> headers,
                                           T params) throws IllegalAccessException, IOException, InvocationTargetException;
    public <T> String sendJsonForText(HttpClientContext context,String url,
                                           String method, Map<String,? super String> headers,
                                           T params) throws IllegalAccessException, IOException, InvocationTargetException;

    public <T>Map<String,Object> sendJsonForHeader(HttpClientContext context, String url,
                                                   String method, Map<String,? super String> headers,
                                                   T params) throws IllegalAccessException, IOException, InvocationTargetException;
    public <T>Map<String,Object> sendFormForHeader(HttpClientContext context, String url,
                                                   String method, Map<String,? super String> headers,
                                                   T params) throws IllegalAccessException, IOException, InvocationTargetException;
    public <T> CloseableHttpResponse sendFormForResponse(HttpClientContext context, String url,
                                                         String method, Map<String,? super String> headers,
                                                         T params) throws IllegalAccessException, IOException, InvocationTargetException;
    public <T>CloseableHttpResponse sendJsonForResponse(HttpClientContext context, String url,
                                                   String method, Map<String,? super String> headers,
                                                   T params) throws IOException;
}

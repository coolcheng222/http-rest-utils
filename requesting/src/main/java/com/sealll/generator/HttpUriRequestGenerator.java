package com.sealll.generator;

import org.apache.http.client.methods.HttpUriRequest;

import java.net.URISyntaxException;

/**
 * 生成对应请求方式的request对象
 * @author sealll
 * @time 2021/1/27 20:14
 */
public interface HttpUriRequestGenerator {
    /**
     * 获取对应请求对象
     * @param url
     * @param method get post put delete
     * @return
     */
    public HttpUriRequest getUriRequest(String url,String method) throws URISyntaxException;
}

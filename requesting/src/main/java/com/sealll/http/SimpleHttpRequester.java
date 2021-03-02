package com.sealll.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sealll.beanutils.BeanParamUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sealll
 * @describe
 * @time 2020/11/22 20:37
 */
public class SimpleHttpRequester implements HttpRequester{
    private Gson gson = new Gson();
    private JsonParser parser = new JsonParser();
    private CoreRequest coreRequest = new CoreRequest();

    /**
     *
     * @param <T>
     * @param context context
     * @param headers headers
     * @param params request params
     * @return
     */
    public <T> String sendFormForText(HttpClientContext context, String url,
                                      String method, Map<String, ? super String> headers,
                                      T params) throws IllegalAccessException, IOException, InvocationTargetException {
        return coreRequest.getString(context, url, method, headers, BeanParamUtils.getEntity(params));

    }
    public <T> JsonElement sendFormForJson(HttpClientContext context,String url,
                                                  String method, Map<String,? super String> headers,
                                                 T params) throws IllegalAccessException, IOException, InvocationTargetException {
        String s = coreRequest.getString(context, url, method, headers, BeanParamUtils.getEntity(params));;
        return parser.parse(s);
    }

    public <T> String sendJsonForText(HttpClientContext context,String url,
                                             String method, Map<String,? super String> headers,
                                             T params) throws IllegalAccessException, IOException, InvocationTargetException {
        return coreRequest.getString(context, url, method, headers, BeanParamUtils.getEntity(params));

    }

    public <T> JsonElement sendJsonForJson(HttpClientContext context, String url,
                                                 String method, Map<String,? super String> headers,
                                                 T params) throws IllegalAccessException, IOException, InvocationTargetException {
        String s = coreRequest.getString(context, url, method, headers, BeanParamUtils.getEntity(params,true));
        return parser.parse(s);
    }
    public <T>Map<String,Object> sendFormForHeader(HttpClientContext context, String url,
                                                  String method, Map<String,? super String> headers,
                                                  T params) throws IllegalAccessException, IOException, InvocationTargetException {
        List<Header> list = coreRequest.getHeaders(context, url, method, headers, BeanParamUtils.getEntity(params));
        HashMap<String, Object> responseHeaders = new HashMap<String, Object>();
        for (Header ele : list) {
            responseHeaders.put(ele.getName(),ele.getValue());
        }
        return responseHeaders;

    }

    @Override
    public <T> CloseableHttpResponse sendFormForResponse(HttpClientContext context, String url, String method, Map<String,? super String> headers, T params) throws IllegalAccessException, IOException, InvocationTargetException {
        return coreRequest.getWholeResponse(context,url,method,headers,BeanParamUtils.getEntity(params));
    }

    @Override
    public <T> CloseableHttpResponse sendJsonForResponse(HttpClientContext context, String url, String method, Map<String,? super String> headers, T params) throws IOException {
        return coreRequest.getWholeResponse(context,url,method,headers,BeanParamUtils.getEntity(params,true));
    }

    public <T>Map<String,Object> sendJsonForHeader(HttpClientContext context, String url,
                                                  String method, Map<String,? super String> headers,
                                                  T params) throws IllegalAccessException, IOException, InvocationTargetException {
        List<Header> list = coreRequest.getHeaders(context, url, method, headers, BeanParamUtils.getEntity(params,true));
        HashMap<String, Object> responseHeaders = new HashMap<String, Object>();
        for (Header ele : list) {
            responseHeaders.put(ele.getName(),ele.getValue());
        }
        return responseHeaders;

    }


}

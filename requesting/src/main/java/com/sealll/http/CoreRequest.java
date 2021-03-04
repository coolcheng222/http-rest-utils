package com.sealll.http;

import com.sealll.beanutils.BeanParamUtils;
import com.sealll.generator.EntityStringGenerator;
import com.sealll.generator.HttpUriRequestGenerator;
import com.sealll.generator.SimpleEntityStringGenerator;
import com.sealll.generator.SimpleHttpUriRequestGenerator;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Base64;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 核心请求发送组件
 * @author sealll
 * @describe
 * @time 2020/12/26 20:47
 */
public class CoreRequest {
    /**
     * httpclient的builder,一个请求一个httpclient
     */
    private HttpClientBuilder hb = HttpClientBuilder.create();
    /**
     * uri request的生成器,用来处理请求方式
     */
    private HttpUriRequestGenerator hrg = new SimpleHttpUriRequestGenerator();
    /**
     * 用来给get请求的url进行包装
     */
    private EntityStringGenerator esg = new SimpleEntityStringGenerator();

    /**
     * 暂存client
     */
    private CloseableHttpClient client = hb.build();

    /**
     * 获取响应体内容
     * @param context context环境
     * @param url url
     * @param method 请求方式
     * @param headers 请求头
     * @param entity2 请求体
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public String getString(HttpClientContext context, String url, String method, Map<String,? super String> headers, HttpEntity entity2) throws InvocationTargetException, IllegalAccessException, IOException {
        try (CloseableHttpResponse response = getResponse(context, url, method, headers, entity2)) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            String s = IOUtils.toString(content,"UTF-8");
            return s;

        }
    }

    //获取响应体的entity
    public HttpEntity getEntity(HttpClientContext context, String url, String method, Map<String, ? super String> headers, HttpEntity entity2) throws IOException {
        try (CloseableHttpResponse response = getResponse(context, url, method, headers, entity2)) {
            return response.getEntity();
        }
    }
    //获取响应头
    public List<Header> getHeaders(HttpClientContext context, String url, String method, Map<String,? super String> headers, HttpEntity entity2) throws IOException {
        try (CloseableHttpResponse response = getResponse(context, url, method, headers, entity2)) {
            return Arrays.asList(response.getAllHeaders());
        }
    }

    //获取整个响应
    public CloseableHttpResponse getWholeResponse(HttpClientContext context, String url, String method, Map<String,? super String> headers, HttpEntity entity2) throws IOException {
        return getResponse(context, url, method, headers, entity2);
    }

    private CloseableHttpResponse getResponse(HttpClientContext context, String url, String method, Map<String, ? super String> headers, HttpEntity entity2) throws IOException {
        try {
            CloseableHttpResponse execute = null;

            Header[] headers1 = BeanParamUtils.getHeaders(headers);
            HttpUriRequest uriRequest = hrg.getUriRequest(url, method);
            if(uriRequest == null){
                return null;
            }
            if(uriRequest instanceof HttpEntityEnclosingRequestBase){
                uriRequest.setHeaders(headers1);
                ((HttpEntityEnclosingRequestBase) uriRequest).setEntity(entity2);
            }else{
//                String url2 = esg.getParamString(url, (StringEntity) entity2);
//                ((HttpRequestBase)uriRequest).setURI(new URI(url2));
            }
            execute = client.execute(uriRequest, context);
//            return EntityUtils.toString(execute.getEntity(), StandardCharsets.UTF_8);
            return execute;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}

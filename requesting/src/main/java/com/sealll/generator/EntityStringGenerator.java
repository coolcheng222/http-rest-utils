package com.sealll.generator;

import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 将url和stringEntity合成为get方式的uri
 * @author sealll
 * @time 2021/1/27 20:39
 */
public interface EntityStringGenerator {
    /**
     *
     * @param url 包装前的url
     * @param entity 请求参数
     * @return 包装后的url
     * @throws URISyntaxException
     * @throws IOException
     */
    public String getParamString(String url,StringEntity entity) throws URISyntaxException, IOException;
}

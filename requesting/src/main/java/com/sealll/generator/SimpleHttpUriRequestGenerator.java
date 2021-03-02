package com.sealll.generator;

import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author sealll
 * @time 2021/1/27 20:26
 */
public class SimpleHttpUriRequestGenerator implements HttpUriRequestGenerator{
    @Override
    public HttpUriRequest getUriRequest(String url, String method) throws URISyntaxException {
        method = (method == null || "".equals(method))? "GET":method;
        HttpUriRequest obj = Method.valueOf(method.toUpperCase()).getObj();
//        ((HttpRequestBase)obj).setProtocolVersion(HttpVersion.HTTP_1_0);
        ((HttpRequestBase)obj).setURI(new URI(url));
        return obj;
    }
    enum Method{
        GET(new HttpGet()),
        POST(new HttpPost()),
        DELETE(new HttpDelete()),
        PUT(new HttpPut());

        Method() {
        }

        Method(HttpUriRequest obj) {
            this.obj = obj;
        }

        private HttpUriRequest obj;

        public HttpUriRequest getObj() {
            return obj;
        }
    }
}

import com.sealll.generator.SimpleEntityStringGenerator;
import com.sealll.generator.SimpleHttpUriRequestGenerator;
import jdk.jfr.StackTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/**
 * @author sealll
 * @time 2021/1/27 20:47
 */
public class GeneratorTest {
    @Test
    public void test1() throws URISyntaxException {
        SimpleHttpUriRequestGenerator gen1 = new SimpleHttpUriRequestGenerator();
        HttpUriRequest post = gen1.getUriRequest("http://www.baidu.com", "get");
        System.out.println(post.getClass());
        System.out.println(post.getURI());

    }
    @Test
    public void test2() throws IOException, URISyntaxException {
        SimpleEntityStringGenerator gen2 = new SimpleEntityStringGenerator();
        StringEntity e = new StringEntity("起飞=1&b&=2", ContentType.APPLICATION_FORM_URLENCODED);

        String s = gen2.getParamString("http://www.baidu.com", e);
        System.out.println(s);
    }
}

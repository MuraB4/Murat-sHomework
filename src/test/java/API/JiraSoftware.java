package API;

import Pojo.LHPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static Utils.PayLoadUtils.getJsessionCookie;

public class JiraSoftware {

    @Test
    public void test1() throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/search");

        HttpGet get = new HttpGet(builder.build());
        get.setHeader("Accept", "application/json");
        get.setHeader("Cookie", getJsessionCookie());

        HttpResponse response = client.execute(get);

        Assert.assertEquals(200, response.getStatusLine().getStatusCode());

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        LHPojo parsedResponse = mapper.readValue(response.getEntity().getContent(), LHPojo.class);

        for (int i=0; i<parsedResponse.getIssues().size(); i++){
            System.out.println(parsedResponse.getIssues().get(i).getFields().getSummary());
        }

    }
}

package Utils;

import Pojo.CAPojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayLoadUtils {
    public static String getPetPayload(int id, String name, String status){
        return "{\n" +
                "  \"id\":"+id+ ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+status+"\"\n" +
                "}";

    }

    public static String generateStringFromResource(String path) throws IOException {
        String petPayLoad = new String(Files.readAllBytes(Paths.get(path)));

        return  petPayLoad;
    }

    public static String getCookieAuthPayLoad(){
        return "{\n" +
                "\"username\": \"KurbanAli\",\n" +
                "\"password\": \"Murat1988\"\n" +
                "}";
    }

    public static String getJiraIssuePayLoad(String summery, String description, String issueType){

        return "{\n" +
                "    \"fields\":{\n" +
                "        \"project\":{\n" +
                "            \"key\": \"MUR\"\n" +
                "        },\n" +
                "        \"summary\": \""+summery+"\",\n" +
                "        \"description\": \""+description+"\",\n" +
                "        \"issuetype\": {\n" +
                "            \"name\": \""+issueType+"\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

    public static String getJsessionCookie() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http");
        builder.setHost("localhost");
        builder.setPort(8080);
        builder.setPath("rest/auth/1/session");

        HttpPost post = new HttpPost(builder.build());

        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");

        HttpEntity entity = new StringEntity(getCookieAuthPayLoad());
        post.setEntity(entity);
        HttpResponse response = client.execute(post);

        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        CAPojo deserialize = mapper.readValue(response.getEntity().getContent(), CAPojo.class);

        String cookieName = deserialize.getSession().get("name");
        String cookieValue = deserialize.getSession().get("value");

        return String.format("%s=%s", cookieName, cookieValue);
    }
}

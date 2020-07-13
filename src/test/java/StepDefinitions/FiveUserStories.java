package StepDefinitions;

import Pages.JiraSoftware.BacklogPage;
import Utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;

import static Utils.PayLoadUtils.getJiraIssuePayLoad;
import static Utils.PayLoadUtils.getJsessionCookie;

public class FiveUserStories {

    WebDriver driver = Driver.getDriver("chrome");
    BacklogPage page;

    @Given("user creates five user story {string}, {string}, {string}")
    public void user_creates_five_user_story(String summery, String description, String issueType) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("localhost").setPort(8080).setPath("rest/api/2/issue");

        HttpPost post = new HttpPost(builder.build());
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Cookie", getJsessionCookie());

        HttpEntity entity = new StringEntity(getJiraIssuePayLoad(summery, description, issueType));
        post.setEntity(entity);

        HttpResponse response = client.execute(post);

        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusLine().getStatusCode());

    }

    @When("user opens backlog page")
    public void user_opens_backlog_page() {
        page = new BacklogPage(driver);
        page.backLogButton.click();
    }

    @Then("user should be able to validate created User Stories and Bugs with deserialized response")
    public void user_should_be_able_to_validate_created_User_Stories_and_Bugs_with_deserialized_response() {
        for (int i=0; i<page.stories.size(); i++){
            Assert.assertTrue(page.stories.get(i).isDisplayed());
            Assert.assertTrue(page.bugs.get(i).isDisplayed());
        }
    }
}

package api.Actions;

import api.base.BaseApiTest;
import api.pojo.UserPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class UserAction {
    private final String BASE_URL= TestUtils.getProperty("BASE_URL");
    private JsonNode node;
    private String endpoint;
    UserPojo expUserPojo;
    UserPojo actUserPojo;
    private Response response;
    ObjectMapper mapper = new ObjectMapper();

    public Response createANewUser() {

        node = TestUtils.JsonReader("UserForPost");
        endpoint=TestUtils.getProperty("UserEndPoint");
        expUserPojo =new UserPojo();
        expUserPojo.setId(node.get("id").asInt());
        expUserPojo.setUsername(node.get("username").asText());
        expUserPojo.setFirstName(node.get("firstName").asText());
        expUserPojo.setLastName(node.get("lastName").asText());
        expUserPojo.setEmail(node.get("email").asText());
        expUserPojo.setPassword(node.get("password").asText());
        expUserPojo.setPhone(node.get("phone").asText());
        expUserPojo.setUserStatus(node.get("id").asInt());

        response= BaseApiTest.getRequest(BASE_URL,endpoint).body(expUserPojo).post();
        Log.info("the response is " + response.body().asString());
        ExtentManager.getExtentTest().info("the response is " + response.body().asString());
        return response;
    }


    public Response createAListOfUsers() throws IOException {

        endpoint = TestUtils.getProperty("UserWithCreateWithListEndPoint");
        String jsonpath = TestUtils.getProperty("ListOfUsersObjectWithArray");
        File file=new File(jsonpath);

        List<UserPojo> users = mapper.readValue(file, new TypeReference<List<UserPojo>>() {
        });

        response = BaseApiTest.getRequest(BASE_URL, endpoint).body(users).post();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public Response createAListOfUsersInArray() throws IOException {

        endpoint = TestUtils.getProperty("UserWithCreateWithArrayEndPoint");
        String jsonpath = TestUtils.getProperty("ListOfUsersObjectWithArray");
        File file=new File(jsonpath);

        List<UserPojo> users = mapper.readValue(file, new TypeReference<List<UserPojo>>() {
        });

        response = BaseApiTest.getRequest(BASE_URL, endpoint).body(users).post();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public Response userLogin(String username, String password) {
        endpoint=TestUtils.getProperty("LoginEndPoint");

        response=BaseApiTest.getRequest(BASE_URL,endpoint).queryParam(username,password).get();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public void valLoginSuccessMsg(String msg) {
        Assert.assertTrue(response.jsonPath().getString("message").contains(msg));
        Log.info(msg + " is present in response. Response:  "+ response.jsonPath().getString("message"));
        ExtentManager.getExtentTest().pass(msg + " is present in response. Response:  "+ response.jsonPath().getString("message"));
    }

    public Response usrLoggedOut() {
        endpoint=TestUtils.getProperty("LogOutEndPoint");
        response=BaseApiTest.getRequest(BASE_URL,endpoint).get();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public Response updateUserDetails(String username) {
        endpoint= TestUtils.getProperty("UserEndPoint")+ "/"+username;
        node=TestUtils.JsonReader("UpdatedUserDetails");
        expUserPojo = new UserPojo();
        expUserPojo.setId(node.get("id").asInt());
        expUserPojo.setUsername(node.get("username").asText());
        expUserPojo.setFirstName(node.get("firstName").asText());
        expUserPojo.setLastName(node.get("lastName").asText());
        expUserPojo.setEmail(node.get("email").asText());
        expUserPojo.setPassword(node.get("password").asText());
        expUserPojo.setPhone(node.get("phone").asText());
        expUserPojo.setUserStatus(node.get("userStatus").asInt());
        response=BaseApiTest.getRequest(BASE_URL,endpoint).body(expUserPojo).put();
        return response;
    }

    public Response deleteTheUser(String username) {
        String endpoint= TestUtils.getProperty("UserEndPoint")+ "/"+username;
        Response response=BaseApiTest.getRequest(BASE_URL,endpoint).delete();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public void valErrorMsg(String username) {

        Assert.assertTrue(response.jsonPath().getString("message").equalsIgnoreCase(username));
        Log.info("username is mentioned in the message "+response.jsonPath().getString("message"));
        ExtentManager.getExtentTest().pass("username is mentioned in the message "+response.jsonPath().getString("message"));
    }

    public Response updatedNotLoggedinUser(String username) {
        endpoint= TestUtils.getProperty("UserEndPoint")+ "/"+username;
        node=TestUtils.JsonReader("NotLoggedInUserDetails");
        expUserPojo = new UserPojo();
        expUserPojo.setId(node.get("id").asInt());
        expUserPojo.setUsername(node.get("username").asText());
        expUserPojo.setFirstName(node.get("firstName").asText());
        expUserPojo.setLastName(node.get("lastName").asText());
        expUserPojo.setEmail(node.get("email").asText());
        expUserPojo.setPassword(node.get("password").asText());
        expUserPojo.setPhone(node.get("phone").asText());
        expUserPojo.setUserStatus(node.get("userStatus").asInt());
        response=BaseApiTest.getRequest(BASE_URL,endpoint).body(expUserPojo).put();
        return response;
    }

    public void registerANewUser() {
        node = TestUtils.JsonReader("RegisterANewUser");
        endpoint=TestUtils.getProperty("UserEndPoint");
        expUserPojo =new UserPojo();
        expUserPojo.setId(node.get("id").asInt());
        expUserPojo.setUsername(node.get("username").asText());
        expUserPojo.setFirstName(node.get("firstName").asText());
        expUserPojo.setLastName(node.get("lastName").asText());
        expUserPojo.setEmail(node.get("email").asText());
        expUserPojo.setPassword(node.get("password").asText());
        expUserPojo.setPhone(node.get("phone").asText());
        expUserPojo.setUserStatus(node.get("userStatus").asInt());

        response= BaseApiTest.getRequest(BASE_URL,endpoint).body(expUserPojo).post();
        Log.info("the response is " + response.body().asString());
        ExtentManager.getExtentTest().info("the response is " + response.body().asString());

    }

    public void loginWithUser() {
        endpoint=TestUtils.getProperty("LoginEndPoint");

        response=BaseApiTest.getRequest(BASE_URL,endpoint).queryParam(node.get("username").asText(),node.get("password").asText()).get();

        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
    }

    public Response getUserDetails(String userid) {
        endpoint=TestUtils.getProperty("UserEndPoint")+"/"+userid;
        response=BaseApiTest.getRequest(BASE_URL,endpoint).get();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;

        
    }

    public void validateJSONSchema() {
        String schemaFile=TestUtils.getProperty("UserResponseJSONSchema");
        response.then().assertThat().body(matchesJsonSchema(new File(schemaFile)));
        Log.info("JSON schema matches");
        ExtentManager.getExtentTest().pass("JSON schema matches");
    }
}

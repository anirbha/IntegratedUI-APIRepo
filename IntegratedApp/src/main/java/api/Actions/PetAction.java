package api.Actions;

import api.base.BaseApiTest;
import api.pojo.PetPojo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.delete;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.AssertJUnit.assertEquals;

public class PetAction {
    private final String BASE_URL= TestUtils.getProperty("BASE_URL");
    private Response response;
    private String jsonpath;
    private String jsoncontent;
    ObjectMapper mapper = new ObjectMapper();
    private String endpoint;
    JsonNode node;

    PetPojo expPetPojo;
    PetPojo actPetPojo;

    int id;
    String status;

    public void Response(Response response)
    {
        this.response=response;
    }

    public Response createPet()  {

        node=TestUtils.JsonReader("NewPetId");
        // Access fields dynamically
        String id = node.get("id").asText();
        String name= node.get("name").asText();
        String status=node.get("status").asText();

        expPetPojo=new PetPojo();
        expPetPojo.setId(Integer.parseInt(id));
        expPetPojo.setStatus(name);
        expPetPojo.setStatus(status);
        endpoint=TestUtils.getProperty("NewPetEndPoint");
        response= BaseApiTest.getRequest(BASE_URL,endpoint)
                .body(expPetPojo).post();
        ExtentManager.getExtentTest().info("Post method response "+response.body().asString());
        Log.info("Post method response "+response.body().asString());
        return  response;
    }

    public void validateStatusCode(String code) {
        TestUtils.responseValidator(response,code);

    }

    public void valSameIdAndStatus(Response response,String id, String status) {
        actPetPojo=new PetPojo();
        this.id = response.jsonPath().getInt("id");
        this.status = response.jsonPath().getString("status");

        ExtentManager.getExtentTest().info(response.asString());
        assertEquals("The id matches as expected "+this.id, Integer.parseInt(id), this.id);
        Log.info("The id matches as expected "+this.id);
        ExtentManager.getExtentTest().pass("The id matches as expected "+this.id);
        assertEquals("The status matches as expected "+this.status, status, this.status);
        Log.info("The status matches as expected "+response.jsonPath().getString("status"));
        ExtentManager.getExtentTest().pass("The status matches as expected "+response.jsonPath().getString("status"));
    }

    public Response fetchAPetById(String id)  {

        node=TestUtils.JsonReader("PetGetId");

        // Access fields dynamically
        endpoint=TestUtils.getProperty("PetEndPointGet")+id;

        response= BaseApiTest.getRequest(BASE_URL,endpoint).get();

        Log.info(response.body().asString());
        expPetPojo=new PetPojo();
        expPetPojo.setId(Integer.parseInt(id));
        expPetPojo.setName(node.get("name").asText());
        expPetPojo.setStatus(node.get("status").asText());
        ExtentManager.getExtentTest().info("Get method response " +response);
        Log.info("Get method response "+response);
        return response;
    }

    public void valSameNameandStatus() {
        actPetPojo=new PetPojo();
        actPetPojo.setName(response.jsonPath().getString("name"));
        actPetPojo.setStatus(response.jsonPath().getString("status"));
        Assert.assertEquals(expPetPojo.getName(),actPetPojo.getName(),"name matches with the response");
        Assert.assertEquals(expPetPojo.getStatus(),actPetPojo.getStatus(),"status matches with the response");
    }

    public Response updatePetbyPut() {

        node=TestUtils.JsonReader("PetPutId");
        endpoint=TestUtils.getProperty("NewPetEndPoint");

        expPetPojo=new PetPojo();
        expPetPojo.setId(node.get("id").asInt());
        expPetPojo.setName(node.get("name").asText());
        expPetPojo.setStatus(node.get("status").asText());
        response= BaseApiTest.getRequest(BASE_URL,endpoint).body(expPetPojo).post();
        Log.info(response.asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public void valNameandStatus() {

        actPetPojo=new PetPojo();
        actPetPojo.setName(response.jsonPath().getString("name"));
        actPetPojo.setStatus(response.jsonPath().getString("status"));

        Assert.assertEquals(actPetPojo.getName(),expPetPojo.getName(),"name validation");
        Log.info("Name is matched with response "+ actPetPojo.getName());
        ExtentManager.getExtentTest().pass("Name is matched with response "+ actPetPojo.getName());

        Assert.assertEquals(actPetPojo.getStatus(),expPetPojo.getStatus(),"status validation");
        Log.info("Status is matched with response "+ expPetPojo.getStatus());
        ExtentManager.getExtentTest().pass("Status is matched with response "+ expPetPojo.getStatus());
    }

    public Response deleteId(String id) {
        createPet();

        endpoint=TestUtils.getProperty("PetEndPointGet")+id;

        response=BaseApiTest.getRequest(BASE_URL,endpoint).header("api_key","special-key").delete();

        Log.info("After delete response " + response.body().asString());
        ExtentManager.getExtentTest().info("After delete response " + response.asString());
        return response;
    }

    public Response findByStatus(String status) {
        endpoint=TestUtils.getProperty("PetFindByStatus");
        response= BaseApiTest.getRequest(BASE_URL,endpoint).queryParam("status",status).when().get();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public void createPetWithPhoto(String id) throws IOException {
        jsonpath = TestUtils.getProperty("NewPetId");
        jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
        node = mapper.readTree(jsoncontent);
        // Access fields dynamically
         id = node.get("id").asText();
        String name= node.get("name").asText();
        String status=node.get("status").asText();

        expPetPojo=new PetPojo();
        expPetPojo.setId(Integer.parseInt(id));
        expPetPojo.setName(name);
        expPetPojo.setStatus(status);
        endpoint=TestUtils.getProperty("NewPetEndPoint");
        response= BaseApiTest.getRequest(BASE_URL,endpoint)
                .body(expPetPojo).post();
        ExtentManager.getExtentTest().info("Post method response "+response.asString());
        Log.info("Post method response "+response.asString());
    }

    public void createPetWithoutField(String field) {
        if(field.equalsIgnoreCase("status"))
        {
            jsonpath = TestUtils.getProperty("NewPetId");
            try{
                jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
                node = mapper.readTree(jsoncontent);
            } catch (IOException e) {
                Log.info("Exception occurred "+ e);
            }

        }
    }

    public void valErrorMsg(String msg) {
        Assert.assertEquals(response.jsonPath().getString("message"),msg,"Error message validation for invalid pet id");
        Log.info("Error message is matching as expected "+response.jsonPath().getString("message"));
        ExtentManager.getExtentTest().info("Error message is matching as expected "+response.jsonPath().getString("message"));
    }

    public Response fetchByInvalidPetId(String id) {
        endpoint=TestUtils.getProperty("PetEndPointGet")+id;

        response= BaseApiTest.getRequest(BASE_URL,endpoint).get();

        Log.info(response.body().asString());

        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public Response updatePetbyPutWithInvalidData() {
        jsonpath = TestUtils.getProperty("InvalidPayLoadForPut");
        endpoint=TestUtils.getProperty("NewPetEndPoint");
        try {
            jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
            node = mapper.readTree(jsoncontent);

        } catch (IOException e) {
            Log.info("exception occurred" + e);
        }

        response= BaseApiTest.getRequest(BASE_URL,endpoint).body(jsoncontent).post();
        Log.info(response.asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
        }

    public void validateResponseNull() {
        Assert.assertTrue(response.asString().equals("[]"));
        Log.info("Response is null");
        ExtentManager.getExtentTest().info("Response is null");
    }

    public void valSameIdAndStatus() {
        id = response.jsonPath().getInt("id");
        status = response.jsonPath().getString("status");
        actPetPojo=new PetPojo();
        actPetPojo.setId(id);
        actPetPojo.setStatus(status);
        ExtentManager.getExtentTest().info(response.asString());
        assertEquals("The id matches as expected "+actPetPojo.getId(), expPetPojo.getId(), actPetPojo.getId());
        Log.info("The id matches as expected "+actPetPojo.getId());
        ExtentManager.getExtentTest().pass("The id matches as expected "+actPetPojo.getId());
        assertEquals("The status matches as expected "+actPetPojo.getStatus(), expPetPojo.getStatus(), expPetPojo.getStatus());
        Log.info("The status matches as expected "+expPetPojo.getStatus());
        ExtentManager.getExtentTest().pass("The status matches as expected "+expPetPojo.getStatus());
    }
    public void valNameandStatus(Response response) {

        actPetPojo=new PetPojo();
        actPetPojo.setName(response.jsonPath().getString("name"));
        actPetPojo.setStatus(response.jsonPath().getString("status"));

        Assert.assertEquals(actPetPojo.getName(),expPetPojo.getName(),"name validation");
        Log.info("Name is matched with response "+ actPetPojo.getName());
        ExtentManager.getExtentTest().pass("Name is matched with response "+ actPetPojo.getName());

        Assert.assertEquals(actPetPojo.getStatus(),expPetPojo.getStatus(),"status validation");
        Log.info("Status is matched with response "+ expPetPojo.getStatus());
        ExtentManager.getExtentTest().pass("Status is matched with response "+ expPetPojo.getStatus());
    }

    public void JsonSchemaValidation() {
        String schemaFile=TestUtils.getProperty("PetResponseJSONSchema");
        response.then().assertThat().body(matchesJsonSchema(new File(schemaFile)));
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().pass(response.body().asString());
    }
}

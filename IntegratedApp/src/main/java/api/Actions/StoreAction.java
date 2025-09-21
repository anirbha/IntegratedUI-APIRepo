package api.Actions;

import api.base.BaseApiTest;
import api.pojo.OrderPurchasePojo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.io.IOException;
import java.util.Map;

public class StoreAction {
    private final String BASE_URL= TestUtils.getProperty("BASE_URL");
    ObjectMapper mapper = new ObjectMapper();
    OrderPurchasePojo expOrderPurchase;
    OrderPurchasePojo actualOrderPurchase;
    Map<String, Integer> inventory;
    private JsonNode node;
    String id;


    public Response response;
    String endpoint;

    public Response fetchInventoryByStatus() throws IOException {

        endpoint=TestUtils.getProperty("StoreInventoryEndPoint");
        response= BaseApiTest.getRequest(BASE_URL,endpoint).get();
        String json = response.getBody().asString();

        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        inventory = mapper.readValue(json, new TypeReference<>() {
        });
        return response;
    }

    public void printCountForDifferentRslt() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
           Log.info(entry.getKey()+"-->"+entry.getValue());
           ExtentManager.getExtentTest().info(entry.getKey()+"-->"+entry.getValue());
        }
    }

    public Response orderForPurchase() {

        node = TestUtils.JsonReader("OrderPurchasePost");
        endpoint=TestUtils.getProperty("StoreOrderEndPoint");
        expOrderPurchase= new OrderPurchasePojo();
        expOrderPurchase.setId(node.get("id").asInt());
        expOrderPurchase.setPetId(node.get("petId").asInt());
        expOrderPurchase.setQuantity(node.get("quantity").asInt());
        expOrderPurchase.setComplete(node.get("complete").asBoolean());
        expOrderPurchase.setStatus(node.get("status").asText());
        expOrderPurchase.setShipDate(node.get("shipDate").asText());
        response=BaseApiTest.getRequest(BASE_URL,endpoint).body(expOrderPurchase).post();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public void validateOrderRespnseWithPayload() {
        actualOrderPurchase= new OrderPurchasePojo();
        actualOrderPurchase.setId(Integer.parseInt(response.jsonPath().getString("id")));
        actualOrderPurchase.setPetId(Integer.parseInt(response.jsonPath().getString("petId")));
        actualOrderPurchase.setQuantity(Integer.parseInt(response.jsonPath().getString("quantity")));
        actualOrderPurchase.setComplete(response.jsonPath().getBoolean("quantity"));
        actualOrderPurchase.setStatus(response.jsonPath().getString("status"));
        actualOrderPurchase.setShipDate(response.jsonPath().getString("shipDate"));

        Assert.assertEquals(expOrderPurchase.getId(),actualOrderPurchase.getId(),"Validate id");
        Log.info("Id "+actualOrderPurchase.getId());
        ExtentManager.getExtentTest().pass("Id "+actualOrderPurchase.getId());

        Assert.assertEquals(expOrderPurchase.getPetId(),actualOrderPurchase.getPetId(),"Validate Pet id");
        Log.info("Pet id "+actualOrderPurchase.getPetId());
        ExtentManager.getExtentTest().pass("Pet id "+actualOrderPurchase.getPetId());

        Assert.assertEquals(expOrderPurchase.getQuantity(),actualOrderPurchase.getQuantity(),"Validate Quantity");
        Log.info("Validate Quantity "+actualOrderPurchase.getQuantity());
        ExtentManager.getExtentTest().pass("Validate Quantity "+actualOrderPurchase.getQuantity());

        Assert.assertEquals(expOrderPurchase.getStatus(),actualOrderPurchase.getStatus(),"Validate status");
        Log.info("Status "+ actualOrderPurchase.getStatus());
        ExtentManager.getExtentTest().pass("Status "+ actualOrderPurchase.getStatus());

        Assert.assertEquals(expOrderPurchase.getShipDate().replace("200Z","200"),actualOrderPurchase.getShipDate().replace("+0000",""),"Validate Shipdate");
        Log.info("Shipdate "+ actualOrderPurchase.getShipDate());
        ExtentManager.getExtentTest().pass("Shipdate "+ actualOrderPurchase.getShipDate());
    }

    public Response fetchPurchaseByOrderId(String id) {
        this.id=id;
        endpoint=TestUtils.getProperty("PurchaseOrderIdEndPoint")+id;

        response=BaseApiTest.getRequest(BASE_URL,endpoint).get();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public void valIdInTheResponse() {
        actualOrderPurchase = new OrderPurchasePojo();
        actualOrderPurchase.setId(Integer.parseInt(response.jsonPath().getString("id")));
        Assert.assertEquals(actualOrderPurchase.getId(),Integer.parseInt(id),"Id validation");
        Log.info("Id matches "+actualOrderPurchase.getId());
        ExtentManager.getExtentTest().pass("Id matches "+actualOrderPurchase.getId());
    }

    public Response delPurchaseByOrderId(String id) {
        this.id=id;
        endpoint=TestUtils.getProperty("PurchaseOrderIdEndPoint")+id;

        response=BaseApiTest.getRequest(BASE_URL,endpoint).delete();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }

    public void valIdInMessage() {
        actualOrderPurchase = new OrderPurchasePojo();
        actualOrderPurchase.setId(Integer.parseInt(response.jsonPath().getString("message")));

        Assert.assertEquals(actualOrderPurchase.getId(),Integer.parseInt(id), "Id matches");
        Log.info("Deleted id mentioned in the message "+actualOrderPurchase.getId());
        ExtentManager.getExtentTest().pass("Deleted id mentioned in the message "+actualOrderPurchase.getId());
    }

    public void valErrorMsg(String msg) {
        Assert.assertEquals(response.jsonPath().getString("message"),msg,"Error message validation");
        Log.info("Error message matches with the expected one");
        ExtentManager.getExtentTest().pass("Error message matches with the expected one");
    }

    public Response invalidOrderFrPurchase() {
        node = TestUtils.JsonReader("InvalidOrderForPurchase");
        endpoint=TestUtils.getProperty("StoreOrderEndPoint");
        expOrderPurchase= new OrderPurchasePojo();
        expOrderPurchase.setId(node.get("id").asInt());
        expOrderPurchase.setPetId(node.get("petId").asInt());
        expOrderPurchase.setQuantity(node.get("quantity").asInt());
        expOrderPurchase.setComplete(node.get("complete").asBoolean());
        expOrderPurchase.setStatus(node.get("status").asText());
        expOrderPurchase.setShipDate(node.get("shipDate").asText());
        response=BaseApiTest.getRequest(BASE_URL,endpoint).body(expOrderPurchase).post();
        Log.info(response.body().asString());
        ExtentManager.getExtentTest().info(response.body().asString());
        return response;
    }
}

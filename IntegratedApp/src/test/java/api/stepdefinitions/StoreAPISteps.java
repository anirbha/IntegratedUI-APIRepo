package api.stepdefinitions;

import api.Actions.StoreAction;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import ui.Utils.TestUtils;

import java.io.IOException;

public class StoreAPISteps {
    StoreAction store=new StoreAction();
    private Response response;
    @Given("I fetch the inventories by status")
    public void iFetchTheInventoriesByStatus() throws IOException {
        response=store.fetchInventoryByStatus();
    }

    @Then("the number of pets with different status should be shown")
    public void theNumberOfPetsWithDifferentStatusShouldBeShown() {
        store.printCountForDifferentRslt();
    }

    @Then("the status code should be {string} for inventory")
    public void theStatusCodeShouldBeForInventory(String status) {
        TestUtils.responseValidator(response,status);
    }

    @Given("I order for the purchasing the pet")
    public void iOrderForThePurchasingThePet() {
        response = store.orderForPurchase();
    }

    @Then("response should match with payload")
    public void responseShouldMatchWithPayload() {
        store.validateOrderRespnseWithPayload();
    }

    @Given("I fetch the purchase by order id {string}")
    public void iFetchThePurchaseByOrderId(String id) {
        response=store.fetchPurchaseByOrderId(id);
    }

    @Then("id in the response should match with the id")
    public void idInTheResponseShouldMatchWithTheId() {
        store.valIdInTheResponse();
    }

    @Given("I delete the purchase by order id {string}")
    public void iDeleteThePurchaseByOrderId(String id) {
        response= store.delPurchaseByOrderId(id);
    }

    @Then("id should be present in the message")
    public void idShouldBePresentInTheMessage() {
        store.valIdInMessage();
    }

    @Then("error message should match {string}")
    public void errorMessageShouldMatch(String msg) {
        store.valErrorMsg(msg);
    }

    @Given("I order for the purchasing the pet with invalid Data")
    public void iOrderForThePurchasingThePetWithInvalidData() {
       response=store.invalidOrderFrPurchase();
    }
}

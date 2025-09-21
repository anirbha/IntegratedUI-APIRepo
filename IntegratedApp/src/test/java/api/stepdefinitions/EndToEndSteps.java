package api.stepdefinitions;

import api.Actions.PetAction;
import api.Actions.StoreAction;
import api.Actions.UserAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class EndToEndSteps {
    PetAction petAction=new PetAction();
    StoreAction storeAction=new StoreAction();
    UserAction userAction = new UserAction();
    private Response response;

    @Given("the pet store has {string} pets")
    public void thePetStoreHasPets(String status) {
        petAction.findByStatus(status);
    }

    @Given("a new user registers")
    public void aNewUserRegisters() {
       userAction.registerANewUser();
    }

    @And("the user logs in with the same username")
    public void theUserLogsInWithTheSameUsername() {
        userAction.loginWithUser();
    }

    @When("the user requests the list of {string} pets")
    public void theUserRequestsTheListOfPets(String status) {
        petAction.findByStatus(status);
    }

    @And("the user selects a pet with id {string}")
    public void theUserSelectsAPetWithId(String id) {
        petAction.fetchAPetById(id);
    }

    @And("the user places an order for pet")
    public void theUserPlacesAnOrderForPetWithId() {
        storeAction.orderForPurchase();
    }

    @Then("the order should be created successfully")
    public void theOrderShouldBeCreatedSuccessfully() {
       storeAction.validateOrderRespnseWithPayload();
    }

    @And("the user should be able to retrieve the order status using the order id {string}")
    public void theUserShouldBeAbleToRetrieveTheOrderStatusUsingTheOrderId(String id) {
       response=storeAction.fetchPurchaseByOrderId(id);
    }

    @And("the pet with id {string} should have status {string}")
    public void thePetWithIdShouldHaveStatus(String id, String status) {
        petAction.valSameIdAndStatus(response,id,status);
    }

    @When("the user updates the a pet")
    public void theUserUpdatesTheAPet() {
        response=petAction.updatePetbyPut();
    }

    @Then("the name and status should be same")
    public void theNameAndStatusShouldBeSame() {
       petAction.valNameandStatus(response);
    }
}

package api.stepdefinitions;

import api.Actions.PetAction;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import ui.Utils.TestUtils;

import java.io.IOException;

public class PetAPISteps {
    PetAction petAction=new PetAction();
    private Response response;
    @Given("I create a new pet")
    public void iCreateANewPet() throws IOException {
        response=petAction.createPet();
    }

    @Then("the status code should be {string}")
    public void theStatusCodeShouldBe(String status) {
        TestUtils.responseValidator(response,status);
    }

    @Then("the newly created id should have the same name and status")
    public void theNewlyCreatedIdShouldHaveTheSameNameAndStatus() {
        petAction.valSameIdAndStatus();
    }

    @Given("I fetch a pet by id {string}")
    public void iFetchTheNewlyPetById(String id) {
        response=petAction.fetchAPetById(id);
    }

    @Then("the id should have the same name and status")
    public void theIdShouldHaveTheSameNameAndStatus() {
        petAction.valSameNameandStatus();
    }

    @Given("I update the pet name and status")
    public void iUpdateThePetNameAndStatus() {
        response=petAction.updatePetbyPut();
    }

    @Then("the name and status should be same with the expected one")
    public void theNameAndStatusShouldBeSameWithTheExpectedOne() {
        petAction.valNameandStatus();
    }

    @Given("I delete the pet id {string}")
    public void iDeleteThePetId(String id) {
        response=petAction.deleteId(id);
    }

    @Given("I fetch pets by status {string}")
    public void iFetchPetsByStatus(String status) {
        response=petAction.findByStatus(status);
    }

    @Given("I upload a photo of the pet {string}")
    public void iUploadAPhotoOfThePet(String id) throws IOException {
       petAction.createPetWithPhoto(id);
    }

    @Given("I am trying to create a new pet without {string}")
    public void iAmTryingToCreateANewPet(String field) {
         petAction.createPetWithoutField(field);
    }

    @Then("the response should the error message {string}")
    public void theResponseShouldTheErrorMessage(String msg) {
        petAction.valErrorMsg(msg);
    }

    @Given("I fetch a pet by an invalid id {string}")
    public void iFetchAPetByAnInvalidId(String id) {
        response=petAction.fetchByInvalidPetId(id);
    }

    @Given("I try to update with invalid status")
    public void iTryToUpdateWithInvalidStatus() {
       response=petAction.updatePetbyPutWithInvalidData();
    }

    @Given("I try to delete the pet id {string}")
    public void iTryToDeleteThePetId(String id) {
    response=petAction.deleteId(id);
    }

    @Then("the response should be blank")
    public void theResponseShouldBeNull() {
        petAction.validateResponseNull();
    }
}

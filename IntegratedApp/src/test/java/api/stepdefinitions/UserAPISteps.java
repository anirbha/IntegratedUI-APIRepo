package api.stepdefinitions;

import api.Actions.UserAction;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import ui.Utils.TestUtils;

import java.io.IOException;

public class UserAPISteps {
    private Response response;
    UserAction useraction= new UserAction();

    @Given("I create a new user object")
    public void iCreateANewUserObject() {
       response=useraction.createANewUser();
    }

    @Then("the status should be {string}")
    public void theStatusShouldBe(String status) {
        TestUtils.responseValidator(response,status);
    }

    @Given("I create a list of new users object")
    public void iCreateAListOfNewUsersObject() throws IOException {
       response=useraction.createAListOfUsers();
    }

    @Given("I create a list of users with array")
    public void iCreateAListOfUsersWithArray() throws IOException {
        response=useraction.createAListOfUsersInArray();
    }

    @Given("the user logs in {string} {string}")
    public void theUserLogsIn(String username, String password) {
        response=useraction.userLogin(username,password);
    }

    @Then("user login message should be displayed {string}")
    public void userLoginMessageShouldBeDisplayed(String msg) {
        useraction.valLoginSuccessMsg(msg);
    }

    @Given("the user logged out from the current session")
    public void theUserLoggedOutFromTheCurrentSession() {
        response = useraction.usrLoggedOut();
    }

    @Given("I update a user details with username {string}")
    public void iUpdateAUserDetailsWithUsername(String username) {
        response=useraction.updateUserDetails(username);
    }

    @Given("I delete a user with username {string}{string} which is already logged in")
    public void iDeleteAUserWithUsernameWhichIsAlreadyLoggedIn(String username, String password) {
        theUserLogsIn(username,password);
        response=useraction.deleteTheUser(username);
    }

    @Then("the username should be present in the response {string}")
    public void theUsernameShouldBePresentInTheResponse(String username) {
        useraction.valErrorMsg(username);
    }

    @Given("I delete a user with username {string} which is already not logged in")
    public void iDeleteAUserWithUsernameWhichIsAlreadyNotLoggedIn(String username) {
        response=useraction.deleteTheUser(username);
    }

    @Given("I update a user details with username {string}{string} which are not logged in")
    public void iUpdateAUserDetailsWithUsernameWhichAreNotLoggedIn(String username, String password) {
        theUserLogsIn(username,password);
        response=useraction.updatedNotLoggedinUser(username);
    }


    @Given("I fetch a user details {string}")
    public void iFetchAUserDetails(String userid) {
        response= useraction.getUserDetails(userid);
    }

    @Then("I validate the JSON Schema of it")
    public void iValidateTheJSONSchemaOfIt() {
       useraction.validateJSONSchema();
    }
}

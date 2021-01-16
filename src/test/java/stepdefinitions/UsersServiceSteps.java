package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import models.User;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UsersServiceSteps {

    @Steps
    UserService userService;
    List<User> users = new ArrayList<User>();
    String password = "Test13";

    @Then("I create several user with next parameters")
    public void createUsers(DataTable dataTable) {
        dataTable.asMaps().forEach(it ->
                users.add(generateUserInfo(it.get("username")
                        , it.get("email"), it.get("phone"), it.get("firstname"), it.get("lastname")))
        );

        userService.createUserWithList(users.toArray()).then().log().ifError().statusCode(200);
    }

    @Then("I create several users")
    public void createUsersArray(DataTable dataTable) {
        dataTable.asMaps().forEach(it ->
                users.add(generateUserInfo(it.get("username")
                        , it.get("email"), it.get("phone"), it.get("firstname"), it.get("lastname")))
        );

        userService.createUserWithArray(users.toArray()).then().log().ifError().statusCode(200);
    }

    @Then("I login with user {word} with status {word}")
    public void loginWithUser(String name, String param) {
        if (param.contains("succ")) {
            Assert.assertEquals("User isn't logged", 200, userService.loginWithUser(name, password).statusCode());
        } else {
            Assert.assertNotEquals("User is successfully logged",
                    200, userService.loginWithUser(name + System.currentTimeMillis(), password).statusCode());
        }
    }

    @Then("I logout by current user")
    public void logOutByUser() {
        Assert.assertEquals("User isn't log out", 200, userService.logout().statusCode());

    }

    @Then("I create single user with username {word} and default values for other fields")
    public void createSingleUser(String username) {
        userService.createUser(generateUserInfo(username, "test@test.com", "any", "test", "test"))
                .then().log().ifError().statusCode(200);
    }

    private User generateUserInfo(String username, String email, String phone, String firstname, String lastname) {
        User user = new User();
        user.idUser = new Random().nextInt(50);
        user.email = email;
        user.username = username;
        user.password = password;
        user.firstName = firstname;
        user.lastName = lastname;
        user.userStatus = 1;
        user.phone = phone;
        return user;

    }

    @Then("I update field username to {word} for user {word}")
    public void updateFieldForUser(String value, String userNameValue) {
        User selectedUser = userService.getUserById(userNameValue);
        selectedUser.username = value;
        userService.updateUser(selectedUser, userNameValue).then().log().ifError().statusCode(200);
    }

    @Then("I delete created user {word}")
    public void deleteUser(String userName) {
        if (userName.contains("invalid")) {
            userService.deleteUser(userName).then().log().ifError().statusCode(404);
        } else {
            userService.deleteUser(userName).then().log().ifError().statusCode(200);
        }
    }

}

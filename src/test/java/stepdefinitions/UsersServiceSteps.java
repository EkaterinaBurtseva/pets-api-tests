package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.an.E;
import io.cucumber.java.en.Then;
import models.User;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;
import services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UsersServiceSteps {

    @Steps
    UserService userService;

    @Then("I create several user with next parameters")
    void createUsers(DataTable dataTable) {
        List<User> users = new ArrayList<User>();

        dataTable.asMaps().forEach(it ->
                users.add(generateUserInfo(it.get("username")))

        );

        userService.createUserWithList(users).then().log().ifError().statusCode(200);
    }

    private User generateUserInfo(String username) {
        User user = new User();
        user.idUser = new Random().nextInt(100);
        user.email = "test@test.com";
        user.username = username;
        user.password = "Test13@";
        return user;

    }


}

package stepdefinitions;

import groovy.util.logging.Slf4j;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Order;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import services.StoreService;

import java.time.LocalDateTime;
import java.util.Random;

import static org.reflections.Reflections.log;

@Slf4j
public class OrderServiceSteps  {

    Order order = new Order();

    @Steps
    StoreService storeService;

    @When("I create new order with newly created pet")
    public void createAnOrderForCreatedPet() {
        order = storeService.purchaseAPet(generatOrderInfo(PetsServiceSteps.pet.id, 2))
                .then().log().ifError().statusCode(200).extract().body().jsonPath().getObject("", Order.class);
        log.info(order.toString());
    }

    @Then("make sure that order is successfully created")
    public void checkOrderExistInTheList() {
        Assert.assertTrue("There is no order with id " + order.id, storeService.getOrderById(order.id).petId == (PetsServiceSteps.pet.id));
    }


    @And("pet inventories can be accessed")
    public void petInventoriesCanBeAccessed() {
        Assert.assertTrue("Inventory is empty",
                storeService.getInventory().then().log().ifError().statusCode(200).extract().body().jsonPath().getMap("").size() > 0);
    }

    private Order generatOrderInfo(int petId, int quantity) {
        Order order = new Order();
        order.id = new Random().nextInt(10000);
        order.petId = petId;
        order.quantity = quantity;
        order.shipDate = LocalDateTime.now().toString();
        order.status = "placed";
        order.complete = true;
        return order;

    }

    @Then("I delete created order")
    public void iDeleteCreatedOrder() {
        storeService.deleteOrderFromStore(order.id).then().log().ifError().statusCode(200);
    }
}

package stepdefinitions;

import groovy.util.logging.Slf4j;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Order;
import models.Pet;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import services.PetsService;
import services.StoreService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static org.reflections.Reflections.log;

@Slf4j
public class OrderServiceSteps {

    Order order = new Order();
    static Pet petInfoOrder = new Pet();

    @Steps
    StoreService storeService;

    @When("I create new order with newly created pet")
    public void createAnOrderForCreatedPet() {
        order = storeService.purchaseAPet(generatOrderInfo(PetsServiceSteps.pet.id, 2))
                .then().log().ifError().statusCode(200).extract().body().jsonPath().getObject("", Order.class);
        log.info(order.toString());
    }

    @When("I create new order for existed pet with status {word}")
    public void createNewOrder(String status) {
        List<Pet> petsList = new PetsService().getAllPetsByStatus(status);
        if (status.contains("test")) {
            Assert.assertEquals("Pet with status " + status + " exist in the list", petsList.size(), 0);
        } else {
            petInfoOrder = petsList.get(0);
            storeService.purchaseAPet(generatOrderInfo(petInfoOrder.id, 2)).then().log().ifError().statusCode(200);
        }

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
        order.id = new Random().nextInt(100);
        order.petId = petId;
        order.quantity = quantity;
        order.shipDate = LocalDateTime.now().toString();
        order.status = "placed";
        order.complete = true;
        return order;

    }

    @Then("I delete created order with {word} id")
    public void iDeleteCreatedOrder(String idType) {
        if (idType.contains("invalid")) {
            storeService.deleteOrderFromStore(7772300).then().log().ifError().statusCode(404);
        } else {
            storeService.deleteOrderFromStore(order.id).then().log().ifError().statusCode(200);
        }

    }
}

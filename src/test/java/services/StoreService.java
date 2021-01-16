package services;


import io.restassured.response.Response;
import models.Order;
import net.thucydides.core.annotations.Step;

public class StoreService extends BaseService {
    @Step("purchase a pet with order info ${0}")
    public Response purchaseAPet(Order orderInfo) {
        return setUp().when()
                .body(orderInfo)
                .post("store/order");

    }

    @Step("find order by id ${0}")
    public Order getOrderById(int orderId) {
        return setUp().when().get(String.format("store/order/%s", orderId))
                .then().extract().body().jsonPath().getObject("", Order.class);
    }

    @Step("delete order from the store by id ${0}")
    public Response deleteOrderFromStore(int orderId) {
        return setUp().when()
                .delete(String.format("store/order/%s", orderId));
    }

    @Step("get all inventory for the store")
    public Response getInventory() {
        return setUp().when().get("store/inventory");
    }


}

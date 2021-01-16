package services;

import io.restassured.response.Response;
import models.Pet;
import net.thucydides.core.annotations.Step;

import java.io.File;
import java.util.List;

public class PetsService extends BaseService {

    @Step("get all pets by statuses ${0}")
    public List<Pet> getAllPetsByStatus(String statuses) {
        return setUp().when().get(String.format("pet/findByStatus?status=%s", statuses))
                .then().log().ifError().statusCode(200).extract().body().jsonPath().getList("", Pet.class);

    }

    @Step("get all pets by id ${0}")
    public Pet getAllPetsById(int petId) {
        return setUp().when().get(String.format("pet/", petId))
                .then().log().ifError().statusCode(200).extract().body().jsonPath().getObject(".", Pet.class);

    }

    @Step("upload the image ${1} for pet by id ${0}")
    public Response uploadImageForPetById(int petId, String imagePath){
        return setUp().when()
                .contentType("multipart/form-data")
                .multiPart("file", new File(imagePath), "image/png")
                .post(String.format("pet/%s/uploadImage", petId));

    }
    @Step("add new pet to the store with info ${0}")
    public Response addNewPetToTheStore(Pet petInfo) {
        return setUp().when()
                .body(petInfo)
                .post("pet");

    }

    @Step("update existing pet by given information ${0}")
    public Response updateExistingPet(Pet petInfo) {
        return setUp().when()
                .body(petInfo)
                .put("pet");
    }

    @Step("update pet in the store with form data based on pet id ${0}")
    public Response updatePetInTheStoreWithFormData(int petId) {
        return setUp().when()
                .post(String.format("pet/%s", petId));
    }

    @Step("delete exiting pet by id ${0}")
    public Response deletePetFromStore(int petId) {
        return setUp().when()
                .delete(String.format("pet/%s", petId));
    }


}

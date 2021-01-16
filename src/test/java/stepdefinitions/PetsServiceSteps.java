package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import models.Category;
import models.Pet;
import models.Tag;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import services.PetsService;

import java.util.Random;
import java.util.stream.Collectors;


public class PetsServiceSteps  {

    public static Pet pet = new Pet();
    Category category = new Category();

    @Steps
    PetsService petsService;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("I add new pet to the store with name {word}, image {word} and {word} status")
    public void getNumberOfService(String name, String imageUrl, String status) {
        category = generateCategoryInfo("testCategory");
        pet = petsService.addNewPetToTheStore(generatePetObjectInfoWithImage(0, name, imageUrl, status, category))
                .then().log().ifError().statusCode(200).extract().body().as(Pet.class);
    }

    @Then("make sure that status of pet {word} is {word}")
    public void checkTheStatusOfPet(String petName, String expectedStatus) {
        Assert.assertTrue("Taken pet by status " + expectedStatus + " isn't " + petName,
                petsService.getAllPetsByStatus(expectedStatus).get(0).name.contains(petName));
    }

    @Then("I add for the created pet new image {word}")
    public void addImageForThePet(String imageUrl) {
        petsService.uploadImageForPetById(pet.id, imageUrl)
                .then().log().ifError().statusCode(200);
    }

    @Then("update status for the created pet to {word}")
    public void updateStatusForTheCreatedPet(String status) {
        petsService.updateExistingPet(generatePetObjectInfoWithImage(pet.id, pet.name, "null", status, category))
                .then().log().ifError().statusCode(200);
    }

    @Then("delete pet with name {word} and status {word} from the store")
    public void deletePetFromTheStoreByName(String petName, String petStatus) {
        Pet foundPet = petsService.getAllPetsByStatus(petStatus).stream().filter(p -> p.name.contains(petName)).collect(Collectors.toList()).get(0);
        petsService.deletePetFromStore(foundPet.id).then().log().ifError().statusCode(200);
    }

    private Pet generatePetObjectInfoWithImage(int petId, String name, String imageUrl, String status, Category categoryInfo) {
        Pet petInfo = new Pet();
        if (petId == 0) {
            petInfo.id = new Random().nextInt(1000);
        } else {
            petInfo.id = petId;
        }
        petInfo.photoUrls = new String[]{imageUrl};
        petInfo.name = name;
        petInfo.status = status;
        petInfo.category = categoryInfo;
        petInfo.tags = generateTagInfo("test");
        return petInfo;

    }

    private Category generateCategoryInfo(String categoryName) {
        Category category = new Category();
        category.id = new Random().nextInt(1);
        category.name = categoryName;
        return category;
    }

    private Tag[] generateTagInfo(String tagName) {
        Tag tag = new Tag();
        tag.idTag = new Random().nextInt(2);
        tag.nameTag = tagName;
        Tag[] tags = new Tag[]{tag};
        return tags;
    }

}

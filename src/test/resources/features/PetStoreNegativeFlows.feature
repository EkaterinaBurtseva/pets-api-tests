Feature: Verify basic e2e flows for pets store: negative


  Scenario: Verify that there is no ability to create an order with invalid status
    Given I create new order for existed pet with status test

  Scenario: Verify that there is no ability to delete non-existed order and user
    Given I delete created order with invalid id
    Then I delete created user invalid

  @bug #any user is able to login
  Scenario: Verify non-registered user cannot login
    Given I login with user marryvanTEST with status error

  Scenario: Verify that there is no ability to update pet, update an image and delete pet with invalid id
    Given I update pet in the order to name test and status soldout - error

  Scenario: Verify that only images can be downloaded for the new and existing pet
    #TODO: implement test

  Scenario: Verify that there is no ability to register same user
    #TODO: implement test

  Scenario: Verify that there is no ability to create same pet
    #TODO: implement test

  Scenario: Verify that there is no ability to made the same order
    #TODO: implement test









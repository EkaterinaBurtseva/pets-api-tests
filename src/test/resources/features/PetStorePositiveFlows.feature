Feature: Verify basic e2e flows for pets store : positive

  Background:
    Given I add new pet to the store with name VasyaTestB, image src/test/java/images/dreamyfrog.png and pending status

  Scenario Outline: Verify new pet can be added, updated and deleted from the store
    When I add for the created pet new image <image2>
    And update status for the created pet to <statusNew>
    Then make sure that status of pet <name> is <statusNew>
    And delete pet with name <name> and status <statusNew> from the store
    Examples:
      | name       | statusNew | image2                                |
      | VasyaTestB | failed    | src/test/java/images/dreamyperson.jpg |

  Scenario: Verify that new order can be created, updated and deleted from the store
    Given I add new pet to the store with name test, image null and new status
    When I create new order with newly created pet
    Then make sure that order is successfully created
    And pet inventories can be accessed
    Then I delete created order with valid id

  Scenario Outline: Verify that user can be created, updated and deleted
    Given I create several user with next parameters
      | username   | email          | firstname | lastname | phone |
      | <userName> | test@test.com  | test1     | boba     | 0000  |
      | test2      | test2@test.com | test2     | biba     | 002   |
    And I update field username to <updatedUsername> for user <userName>
    Then I delete created user <updatedUsername>
    Examples:
      | userName | updatedUsername |
      | smth     | Mr.Bin          |

  Scenario Outline: Verify that new created user is able to create an order with existing pet
    When  I create single user with username <username> and default values for other fields
    And I login with user <username> with status success
    And I create new order for existed pet with status pending
    Then I update pet in the order to name <changedName> and status sold - successfully
    Examples:
      | username | changedName |
      | catdog   | vanya       |

  Scenario: Verify new order can be created for new users
    Given I create several users
      | username | email             | firstname | lastname | phone |
      | ragnar   | ragnar@test.com   | ragnar    | lodbrok  | 0000  |
      | lagertha | lagertha@test.com | lagertha  | lodbrok  | 002   |
    Then I login with user ragnar with status success
    When I add new pet to the store with name petX, image src/test/java/images/dreamyfrog.png and sold status
    And I create new order with newly created pet
    And I delete created order with valid id
    Then I logout by current user






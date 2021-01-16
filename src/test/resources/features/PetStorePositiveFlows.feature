Feature: Verify basic e2e flows for pets store

  Scenario Outline: Verify new pet can be added, updated and deleted from the store
    Given I add new pet to the store with name <name>, image <image> and <status> status
    When I add for the created pet new image <image2>
    And update status for the created pet to <statusNew>
    Then make sure that status of pet <name> is <statusNew>
    And delete pet with name <name> and status <statusNew> from the store
    Examples:
      | name   | image                               | status  | statusNew | image2                                |
      | Saimon | src/test/java/images/dreamyfrog.png | created | failed    | src/test/java/images/dreamyperson.jpg |

  Scenario: Verify that new order can be created, updated and deleted from the store
    Given I add new pet to the store with name <name>, image null and <status> status
    When I create new order with newly created pet
    Then make sure that order is successfully created
    And pet inventories can be accessed
    Then I delete created order

  Scenario: Verify that user can be created, updated and deleted
    Given I create several user with next parameters
      | username | email          | phone |
      | test     | test@test.com  | 0000  |
      | test2    | test2@test.com | 002   |
    Then I update field <field> for user <username>


  Scenario: Verify that new created user is able to create an order with existing pet
    Given I create single user with username <username> and password <password>
    Then login to the system with username <username> and password <password>


  Scenario: Verify order can be created for the existing pet and user
    Given login to the system with username <username> and password <password>




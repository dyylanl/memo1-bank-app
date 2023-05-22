Feature: Bank account operations
  Checking bank account operations

  Scenario: Successfully withdraw money when balance is enough
    Given Account with a balance of 1000
    When Trying to withdraw 500
    Then Account balance should be 500

  Scenario: Cannot withdraw more money than the account balance
    Given Account with a balance of 1000
    When Trying to withdraw 1001
    Then Operation should be denied due to insufficient funds
    And Account balance should remain 1000

  Scenario: Successfully deposit money when sum is not negative
    Given Account with a balance of 1000
    When Trying to deposit 500
    Then Account balance should be 1500


  Scenario: Successfully deposit money when sum is not negative with small values
    Given Account with a balance of 1
    When Trying to deposit 1
    Then Account balance should be 2

  Scenario: Cannot withdraw more money than the account balance with big values
    Given Account with a balance of 3400
    When Trying to withdraw 5000
    Then Operation should be denied due to insufficient funds
    And Account balance should remain 3400

  Scenario: Successfully withdraw money when the money to be withdrawn is very close but less than the balance
    Given Account with a balance of 8000
    When Trying to withdraw 7999
    Then Account balance should be 1

  Scenario: Successfully withdraw money when the money to be withdrawn is equals  than the balance
    Given Account with a balance of 8000
    When Trying to withdraw 8000
    Then Account balance should be 0

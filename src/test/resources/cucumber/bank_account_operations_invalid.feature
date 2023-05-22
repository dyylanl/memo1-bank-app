Feature: Bank account operations
  Checking bank account operations

  Scenario: Cannot deposit money when sum is negative
    Given Account with a balance of 100
    When Trying to deposit -500
    Then Operation should be denied due to negative sum
    And Account balance should remain 100


  Scenario: Cannot deposit money when sum is negative with small values
    Given Account with a balance of 9000
    When Trying to deposit -9000
    Then Operation should be denied due to negative sum
    And Account balance should remain 9000

  Scenario: Cannot deposit money when sum is negative with big values
    Given Account with a balance of 2000
    When Trying to deposit -10000
    Then Operation should be denied due to negative sum
    And Account balance should remain 2000

  Scenario: Cannot deposit money when sum is zero
    Given Account with a balance of 1500
    When Trying to deposit 0
    Then Operation should be denied due to negative sum
    And Account balance should remain 1500

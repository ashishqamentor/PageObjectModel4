Feature: vegitable shopping.

Scenario: To verify checkout journey.
Given user is on greenkart site
When user add vegitable into basket
And user perform checkout
Then successfull message displayed


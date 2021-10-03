Feature: Most recent values are displayed
  Verify the user is able to see a summary of the most recent values

  Scenario: View the last sensor reading
    Given the user navigates to the site
    When the user is on the home page
    Then a summary is displayed
    And the summary contains the temperature in celsius with a "℃" label 
    And the summary contains the humidity with a percent "%" label
    And the summary contains the date

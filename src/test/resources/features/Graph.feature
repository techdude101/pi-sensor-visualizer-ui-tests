Feature: View graph feature
  Verify the user is able to see a graph of temperature and humidity

  Scenario: View the graph
    Given the user navigates to the site
    When the user is on the graph page
    And the user enters a date range
    Then a graph is displayed
    And the x-axis title has a label "Date/Time"
    And the left y-axis title has a label "Temperature (°C)"
    And the right y-axis title has a label "Humidity (%)"

Feature: View graph feature - Axes ranges
  Verify the user is able to see a graph with appropriate axes scales

  Scenario: Graph range - Humidity
    Given the user navigates to the site
    When the user is on the graph page
    Then a graph is displayed
    And the right y-axis title has a label "Humidity (%)"
    And the y-axis has a range of 0 to 100

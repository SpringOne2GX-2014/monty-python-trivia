Feature: Trivia

  Scenario: Make sure the main page is displaying correctly
    When I go to the landing page
    Then I expect to see a list of Monty Python movies
    And one of the options should be 'Holy Grail'
Feature: QuestionAndAnswer

  Scenario: Trivia Question and Answer Feature
    Given I am on the first page
    When I select 'Holy Grail'
    And I select 'What do the Knights of Ni say'
    And I press submit
    Then I should see the answer page
    And I should see the question displayed
    And I should see the answer 'Ni!'
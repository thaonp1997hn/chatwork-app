@IOS
Feature: ChatWork

  @run
  Scenario Outline: ChatWork
    Given user opens ChatWork app
    When user chooses Account tab
    And user selects MyChat
    Then MyChat box is displayed
    When user enters "<message>"
    And user taps on "<message>"
    Then verify all functions interacting with message is shown as below
      | function1 | function2 | function3 | function4 | function5 | function6 | function7 | function8 |
      | Edit      | Reaction  | Quote     | Bookmark  | Task      | Link      | Unread    | Delete    |
    When user reacts "<react>" to "<message>"
    Then verify reaction attached to "<message>" is displayed
    When user edits "<message>" to "<edit message>"
    Then verify "<edit message>" is displayed
    When user deletes "<edit message>"
    Then verify "<edit message>" is disappeared

    Examples:
      | message           | edit message       | react |
      | Nice to meet you! | How are you doing? | Like  |
Feature: Stories

  Scenario Outline: Create Five User Stories in Jira

    Given user creates five user story "<summery>", "<description>", "<issueType>"
    Examples:
      | summery     | description                                            | issueType |
      | Story test1 | Here I supposed to write meaningful story description1 | Story     |
      | Story test2 | Here I supposed to write meaningful story description2 | Story     |
      | Story test3 | Here I supposed to write meaningful story description3 | Story     |
      | Story test4 | Here I supposed to write meaningful story description4 | Story     |
      | Story test5 | Here I supposed to write meaningful story description5 | Story     |
      | Bug test1   | Here I supposed to write meaningful story description1 | Bug       |
      | Bug test2   | Here I supposed to write meaningful story description2 | Bug       |
      | Bug test3   | Here I supposed to write meaningful story description3 | Bug       |
      | Bug test4   | Here I supposed to write meaningful story description4 | Bug       |
      | Bug test5   | Here I supposed to write meaningful story description5 | Bug       |

    Scenario: Validate created User Stories and Bugs
      When user opens backlog page
      Then user should be able to validate created User Stories and Bugs with deserialized response
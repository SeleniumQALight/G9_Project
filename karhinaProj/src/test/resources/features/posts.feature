
  Feature: Posts feature

    Background:
        Given I open Home page 'default' user and 'default' password


      @R004
        @deleteAllPostsForDefaultUser
      Scenario Outline: R004 Check number of posts on UI
        Given I create <numberOfPosts> new posts via API for 'default' user and 'default' password
          | title  | Post by API |
          | body   | body post   |
          | select | One Person  |
        When I click on button MyProfile On Header Element
        Then I was redirected to MyProfile Page
        And I see <numberOfPosts> posts in Posts list on MyProfile Page


        Examples:
          | numberOfPosts |
          | 2             |
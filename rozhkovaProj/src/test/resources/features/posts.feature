Feature: Posts feature

  Background: //степи перед сценарієм, наприклад відкриваю пейдж
    Given I open Home page as 'default' user and 'default' password

  @R004
    @deleteAllPostsForDefaultUser
  Scenario Outline: R004 Check number of posts on UI
    Given I create <numberOfPosts> new posts via API for 'default' user and 'default' password
#      параметри, якщо багато, о можемо передавати у вигляди таблички
      | title  | Post by API |
      | body   | body post   |
      | select | One Person  |
    When I click on button MyProfile on Header Element
    Then I was redirected to MyProfile page
    And I see <numberOfPosts> posts in Posts list on MyProfile page




    Examples:
      | numberOfPosts |
      | 2             |

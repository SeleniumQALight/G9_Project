
  Feature: Posts feature

    Background:

      @R004
      Scenario Outline: R004 Check number of posts on UI
        Given I create '<numberOfPosts>' new posts via API for 'default' user and 'default' password
          | title  | Post by API |
          | body   | body post   |
          | select | One Person  |

        Examples:
          | numberOfPosts |
          | 2             |

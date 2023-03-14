@everydayFeed
Feature: Everyday Feed

  Background: 
    Given Open the browser and Launch the application

  Scenario: Verify the Newsroom everyday feed section is displayed in the Newsroom US landing page
    Then User must able to see "Everyday feed" on Newsroom US Landing Page

  Scenario: Validate total number of articles displayed under everyday feed section are 11 in Newsroom US landing page
    Then User must able to see "Latest News" on Newsroom US Landing Page
    Then User must able to see 11 articles should be displayed under Latest News section on Newsroom US Landing Page

  Scenario: Verify if Everyday Feed have 11 articles in reverse chronological order witth one article as Everyday Hero in Newsroom US landing page
    Then User must able to see 11 articles in reverse chronological order witth one article as Everyday Hero in Newsroom US landing page
    And verify the date stamp of hero tile displays current date

  Scenario: Verify if the user can view 11 articles under Everyday feed with 1 Hero Highlight, Two 2up tiles and Two 3up tiles displayed in Large viewports in Newsroom Landing page
    Then verify Everyday article displays 11 article with 1 Hero Highlight Two 2 ups and Two 3 ups in the Newsroom Landing Page
    And compare the date stamp of hero to Two 2 ups in the Newsroom Landing Page
    And compare the date stamp of Two 2 ups to Two 3 ups in the Newsroom Landing Page
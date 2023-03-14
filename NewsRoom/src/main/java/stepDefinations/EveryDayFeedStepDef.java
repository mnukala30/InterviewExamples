package stepDefinations;

import java.text.ParseException;
import java.time.Instant;
import java.util.Properties;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.EveryDayFeedPage;
import util.Browser;
import util.ConfigurationReader;

public class EveryDayFeedStepDef {

	private EveryDayFeedPage everyDayFeedPage = new EveryDayFeedPage(Browser.getDriver());
	ConfigurationReader CR = new ConfigurationReader();
	Properties Prop = CR.init_prop();
	static ExtentTest test;
	static ExtentReports report;

	@Given("Open the browser and Launch the application")
	public void open_the_browser() {
		Browser.getLogger().log(Status.INFO, "Browser is launched successfully");
		Browser.getDriver().get(Prop.getProperty("url"));
		Browser.getLogger().log(Status.INFO, "Loaded with url " + Prop.getProperty("url"));
	}
	@Then("User must able to see {string} on Newsroom US Landing Page")
	public void user_must_able_to_see_on_newsroom_us_landing_page(String pageHeader) {
		if(pageHeader.equals("Everyday feed")) {
			Assert.assertTrue(everyDayFeedPage.everyDayFeedIsDisplayed(), pageHeader + "is verified");
		}else if(pageHeader.equals("Latest News")) {
		Assert.assertTrue(everyDayFeedPage.latestNewsHeadingIsDisplayed(),pageHeader + "is verified");
		}
	}

	@Then("User must able to see {int} articles should be displayed under Latest News section on Newsroom US Landing Page")
	public void user_must_able_to_see_articles_should_be_displayed_under_latest_news_section_on_newsroom_us_landing_page(
			Integer expectedCount) {

		Assert.assertTrue(everyDayFeedPage.getArticlesCountUnderLatestNews() == expectedCount);
	}

	@Then("User must able to see {int} articles in reverse chronological order witth one article as Everyday Hero in Newsroom US landing page")
	public void user_must_able_to_see_articles_in_reverse_chronological_order_witth_one_article_as_everyday_hero_in_newsroom_us_landing_page(Integer int1) {
	
		Assert.assertTrue(everyDayFeedPage.verifyHeroTileDisplaysFirst(),"Hero tile display is verified");
	}
	@Then("verify the date stamp of hero tile displays current date")
	public void user_verify_dateStamp_hero_tile_displays_current_date() {
		String currentTime=Instant.now().toString().split("-")[2].substring(0,2);  // Capture the current moment in UTC, with a resolution as fine as nanoseconds. Typically captured in microseconds in Java 9 and later, milliseconds in Java 8.
		String heroTileDisplayTime = everyDayFeedPage.getHeroTileDateStamp().split("-")[2].substring(0,2);
		Assert.assertEquals(heroTileDisplayTime, currentTime,"Verified the hero tile date with current date");
		
	}
	
	@Then("verify Everyday article displays {int} article with {int} Hero Highlight Two {int} ups and Two {int} ups in the Newsroom Landing Page")
	public void verify_everyday_article_displays_article_with_hero_highlight_two_ups_and_two_ups_in_the_newsroom_landing_page(Integer int1, Integer heroTile, Integer twoUps, Integer threeUps) {
		Assert.assertEquals(everyDayFeedPage.twoUpSecondaryArticles(), twoUps*2,"Two 2 ups are verified in page");
		Assert.assertEquals(everyDayFeedPage.threeUpArticles(), threeUps*2,"Three 2 ups are verified in page");
		
	}
	

	@Then("compare the date stamp of hero to Two {int} ups in the Newsroom Landing Page")
	public void compare_the_date_stamp_of_hero_to_two_ups_in_the_newsroom_landing_page(Integer int1) throws InterruptedException, ParseException {
	   
		Assert.assertTrue(everyDayFeedPage.compareDateTimeStampsTwoUpTilesToHero(),"Two up tiles date comparison is verified");
		
	}

	@Then("compare the date stamp of Two {int} ups to Two {int} ups in the Newsroom Landing Page")
	public void compare_the_date_stamp_of_two_ups_to_two_ups_in_the_newsroom_landing_page(Integer int1, Integer int2) throws InterruptedException, ParseException {
		Assert.assertTrue(everyDayFeedPage.compareDateTimeStampsThreeUpTiles(),"Three Two up tiles date comparison is verified");
	}


	

	@Then("User must able to see the pattern of fourteen article are OneUp plus OneUp plus OneUp plus TwoUp plus TwoUp TwoUp plus TwoUp plus ThreeUp in Takeover section of Newsroom US landing page")
	public void user_must_able_to_see_the_pattern_of_fourteen_article_are_1up_1up_1up_2up_2up_2up_2up_3up_in_takeover_section_of_newsroom_us_landing_page()  {
		Assert.assertTrue(everyDayFeedPage.twoUpSecondaryArticles() <= 8);
		Assert.assertTrue(everyDayFeedPage.threeUpArticles() <= 3);
		
	}


	@Then("User must able to see the newsroom archives page")
	public void user_must_able_to_see_the_newsroom_archives_page() {
	   
		String actualTitle=Browser.getDriver().getTitle();
		String expectedTitle= "All Topics Archive - Apple";
		Assert.assertEquals(expectedTitle, actualTitle);
		
	}
	

//	@Then("User must see the Newsroom Uzh-CN landing page")
//	public void user_must_see_the_newsroom_uzh_cn_landing_page() {
//		String actualTitle=Browser.getDriver().getTitle();
//		String expectedTitle= "Newsroom - Apple (中国大陆)";
//		Assert.assertEquals(expectedTitle, actualTitle);
//	}


//	@Then("User should directed to newsroom archives page")
//	public void user_should_directed_to_newsroom_archives_page() {
//		String actualTitle=Browser.getDriver().getTitle();
//		String expectedTitle= "All Topics Archive - Apple";
//		Assert.assertEquals(expectedTitle, actualTitle);
//		
//	}

}

	
	


package pageObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.ElementUtil;
import util.GenericObjects;

public class EveryDayFeedPage {
	GenericObjects GO= new GenericObjects();
	ElementUtil EU = new ElementUtil();
	
	public EveryDayFeedPage(WebDriver driver)

	 {
		PageFactory.initElements(driver,this);

	}
	
	@FindBy(xpath="//section[@id='everydayfeed']")
	public WebElement everyDayFeed;
	
	@FindBy(xpath="//section[@id='everydayfeed']//h2[@class='section-head']")
	public WebElement latestNews;
	
	@FindBy(xpath="//section[@id='everydayfeed']//ul[@class='section-tiles']//li")
	public List<WebElement> articlesList;
	
	@FindBy(xpath="(//section[@id='everydayfeed']//ul[@class='section-tiles']//li)[1]/a")
	public WebElement heroTile;
	
	@FindBy(xpath="(//section[@id='everydayfeed']//ul[@class='section-tiles']//li)[1]//div[contains(@class,'tile__timestamp')]")
	public WebElement heroTileDateStamp;
	
	@FindBy(xpath="//div[@class='tile__timestamp icon icon-before icon-clock']")
	public WebElement latestNewsTimeStamp; 
	
	@FindBy(xpath="//li[@class='tile-item item-2up']")
	public List<WebElement> item2upList;
	
	@FindBy(xpath="//li[@class='tile-item item-3up']")
	public List<WebElement> item3upList;
	
	@FindBy(xpath="//div[@class='(//div[@class='tile__headline'])[2]']")
	public WebElement latestNewsTimeStamp_2up_1;
		
	@FindBy(xpath="//div[@class='(//div[@class='tile__timestamp icon-hide icon icon-before icon-clock'])[1]")
	public WebElement latestNewsTimeStamp_2up_2;
	
	@FindBy(xpath="//div[@class='ac-ln-menu-tray']")
	public WebElement SearchnewsroomandpopularTopics;
	
	@FindBy(xpath="//li[@class='ac-ln-menu-item'][1]//following-sibling::li[1]")
	public WebElement popularTopicsBesideSearchNewsroom;
	
	
	@FindBy(xpath="//a[@href='/newsroom/']//parent::span//following-sibling::div")
	public WebElement newsroombesideSearchNewsroom;
	
	@FindBy(xpath="//span[@class='ac-ln-title']//a")
	public WebElement newsroomHyperlink;
	
	@FindBy(xpath="//button[@class='btn-topics ac-ln-menu-link']")
	public WebElement popularTopic;
	
	@FindBy(xpath="//div[@class='topic-group'][3]//following-sibling::div[4]//child::a")
	public WebElement macInPopularTopic;
	
	
	public boolean SearchnewsroomandpopularTopicsIsDisplayed() {
		boolean result=false;
		if(SearchnewsroomandpopularTopics.isDisplayed()) {
		result=true;
	}
		return result;
		
	}


	public int getArticlesCountUnderLatestNews() {
		return(articlesList.size());
	}


	public Boolean latestNewsHeadingIsDisplayed() {
		return(latestNews.isDisplayed());
		
	}

	public Boolean everyDayFeedIsDisplayed() {
		return(everyDayFeed.isDisplayed());
		
	}

	public Boolean verifyHeroTileDisplaysFirst() {
		Boolean result = false;
		try {
			result= EU.returnAttribute(heroTile, "class").contains("hero");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getHeroTileDateStamp() {
		String dateStamp = null;
		try {
			dateStamp = EU.returnAttribute(heroTileDateStamp, "data-ts");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateStamp;
	}

	public int twoUpSecondaryArticles() {
		return item2upList.size();
	}


	public int threeUpArticles() {
		return item3upList.size();
	}

	private By getLiDivClass(String className, String classNameDt,String linkNumber) {
		return(By.xpath("(//li[@class='"+className+"'])["+linkNumber+"]//div[contains(@class,'"+classNameDt+"')]"));
	}
	
	public Boolean compareDateTimeStampsTwoUpTilesToHero() throws InterruptedException, ParseException {
		Boolean result= false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
		Date herodateStamp=dateFormat.parse(getHeroTileDateStamp());
		List<Date> timeStamps = new ArrayList<Date>();
		timeStamps.add(herodateStamp);
		for(int i=1;i<=item2upList.size();i++) {
			String timeStamp=EU.returnAttribute(getLiDivClass("tile-item item-2up", "tile__timestamp ",String.valueOf(i)), "data-ts");
			timeStamps.add(dateFormat.parse(timeStamp));
		}
		for(int i=0;i<timeStamps.size();i++) {
			if(i==timeStamps.size()-1) {
				break;
			}
			if(timeStamps.get(i).after(timeStamps.get(i+1))||timeStamps.get(i).equals(timeStamps.get(i+1))) {
				result=true;
			}else {
				result=false;
			}
			
		}
		return result;
	}


	public Boolean compareDateTimeStampsThreeUpTiles() throws InterruptedException, ParseException {
		Boolean result= false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
		List<Date> twoTiletimeStamps = new ArrayList<Date>();
		for(int i=1;i<=item2upList.size();i++) {
			String timeStamp=EU.returnAttribute(getLiDivClass("tile-item item-2up", "tile__timestamp ",String.valueOf(i)), "data-ts");
			twoTiletimeStamps.add(dateFormat.parse(timeStamp));
		}
		List<Date> threeTiletimeStamps = new ArrayList<Date>();
		for(int i=1;i<=item3upList.size();i++) {
			String timeStamp=EU.returnAttribute(getLiDivClass("tile-item item-3up", "tile__timestamp ",String.valueOf(i)), "data-ts");
			threeTiletimeStamps.add(dateFormat.parse(timeStamp));
		}
		List<Date> allDateStamps=  new ArrayList<Date>();
		allDateStamps.addAll(twoTiletimeStamps);
		allDateStamps.addAll(threeTiletimeStamps);
		for(int i=0;i<allDateStamps.size();i++) {
			if(i==allDateStamps.size()-1) {
				break;
			}
			if(allDateStamps.get(i).after(allDateStamps.get(i+1))||allDateStamps.get(i).equals(allDateStamps.get(i+1))) {
				result=true;
			}else {
				result=false;
			}
			
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

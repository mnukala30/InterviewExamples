package runner;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.reporter.ExtentReporter;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/"
				}, 
		monochrome = true,
		glue = { "stepDefinations" },
		features = { "src/test/resources/Features" },
		tags="@everydayFeeds",
		publish=true
		
)

public class ParallelRun extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
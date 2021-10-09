package runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;



@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.stepDefinition","com.hook"},
        plugin={"json:target/cucumber.json", "pretty",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags= "~@ignore")
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {


    @DataProvider(parallel = true)
    public Object[][] scenario(){
        return super.scenarios();
    }


}

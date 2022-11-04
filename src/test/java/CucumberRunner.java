import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        plugin = {"pretty", "html:target/cucumber/cucumber-report.html", "junit:target/cucumber/cucumber-report.xml"},
        glue = {"steps"},
        monochrome = true)
public class CucumberRunner {
}

package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"classpath:features"},
        monochrome = false,
        glue = {"helpers", "stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report.html", "json:target/report.json"},
        tags = "@login01 or @login02"
)
public class RunCucumberTest {
}

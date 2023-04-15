package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"classpath:features"},
        monochrome = true,
        glue = {"helpers", "stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report.html", "json:target/report.json"},
        tags = "@tag"
)
public class RunCucumberTest {
}

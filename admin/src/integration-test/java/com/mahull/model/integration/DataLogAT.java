package com.mahull.model.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "html:target/cucumber-html-report",
        features = "classpath:features",
        glue = "com.mahull.integration",
        tags = "~@wip")
public class DataLogAT {
}

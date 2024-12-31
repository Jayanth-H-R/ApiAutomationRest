package com.bdd.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = ("./src/test/java/com/bdd/features"),
        glue = {"com.bdd.stepdefination"},
        monochrome = true,
        dryRun = false ,
        plugin = {"pretty","html:target/cucmber-html-report.html"}
)
public class RunnerPet  {

}

package com.mahull.model.integration.steps;


import com.mahull.model.integration.config.DataLogTestConfig;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(classes = {DataLogTestConfig.class})
public class AbstractStep {

    private MockMvc mockMvc;

    private MockRestServiceServer mockServer;
    @Before
    public void init() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(loginConroller).build();
//        this.mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @After
    public void destroy() {

    }

    @Given("^I have a calculator$")
    public void i_have_a_calculator() throws Throwable {
//        this.mockServer.expect(requestTo("http://localhost:8080/test"))
//                .andRespond(withSuccess("hello", MediaType.APPLICATION_JSON));
//        ResultActions perform = this.mockMvc.perform(get("/"));

//        assertEquals(perform.andReturn().getResponse().getContentAsString(), "hello");
    }

    @When("^I add (\\d+) and (\\d+)$")
    public void i_add_and(int arg1, int arg2) throws Throwable {

    }

    @Then("^the result should be (\\d+)$")
    public void the_result_should_be(int arg1) throws Throwable {

    }
}

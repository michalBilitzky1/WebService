package com.webservice.WebService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
public class AdditionAndMultiplicationTest {
    private MockMvc mockMvc;
    @InjectMocks
    private AdditionAndMultiplication additionAndMultiplication;
    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(additionAndMultiplication).build();
    }

    /**
     * choose illegal operation - not addition or multiplication.
     * should return the user the massage: "No such operation"
     */
    @Test
      public void NotLegalOperation() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/blabla/65/875")
      ).andExpect(MockMvcResultMatchers.content().string("No such operation"));
    }

    /**
     * the user's input isn't 2 numbers.
     * should return the user the massage: "You must choose 2 numbers"
     */
    @Test
    public void hasToReceiveNumbers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/addition/6a/5")
        ).andExpect(MockMvcResultMatchers.content().string("You must choose 2 numbers"));
    }

    /**
     * add 2 long numbers.
     * should return their sum.
     */
    @Test
    public void calculateAdditionLongNumbers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/addition/5921064860/396719568")
        ).andExpect(MockMvcResultMatchers.content().string("6317784428"));
    }

    /**
     * multiply 2 long numbers.
     * should return their multiplication result.
     */
    @Test
    public void calculateMultiplicationLongNumbers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/multiplication/19210/396719")
        ).andExpect(MockMvcResultMatchers.content().string("7620971990"));
    }

    /**
     * multiply with 0.
     * should return 0.
     */
    @Test
    public void MultiplicationWithZero() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/multiplication/58/0")
        ).andExpect(MockMvcResultMatchers.content().string("0"));
    }

    /**
     * add 2 numbers from the example in the pdf task.
     * should return their sum.
     */
    @Test
    public void ExampleFromTask1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/addition/111/456")
        ).andExpect(MockMvcResultMatchers.content().string("567"));
    }

    /**
     * add 2 numbers from the example in the pdf task.
     * should return their sum.
     */
    @Test
    public void ExampleFromTask2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/addition/999/1")
        ).andExpect(MockMvcResultMatchers.content().string("1000"));
    }

    /**
     * add 2 numbers from the example in the pdf task.
     * should return their sum.
     */
    @Test
    public void ExampleFromTask3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/addition/99/11")
        ).andExpect(MockMvcResultMatchers.content().string("110"));
    }

    /**
     * multiply 2 numbers from the example in the pdf task.
     * should return their multiplication result.
     */
    @Test
    public void ExampleFromTask4() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/multiplication/11/11")
        ).andExpect(MockMvcResultMatchers.content().string("121"));
    }

    /**
     * multiply 2 numbers from the example in the pdf task.
     * should return their multiplication result.
     */
    @Test
    public void ExampleFromTask5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/multiplication/999/999")
        ).andExpect(MockMvcResultMatchers.content().string("998001"));
    }
}
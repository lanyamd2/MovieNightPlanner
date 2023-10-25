package com.sparta.projectmovie1.movienightplanner.webcontrollers;

import com.sparta.projectmovie1.movienightplanner.models.Production;
import com.sparta.projectmovie1.movienightplanner.models.ProductionList;
import com.sparta.projectmovie1.movienightplanner.services.SearchService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {

    @Autowired
    MockMvc mockMvc;


    @MockBean
    SearchService searchService;

    @Test
    public void showIndexPageTest() throws Exception {

        Production p1=new Production();
        List<Production> productions=new ArrayList<>();
        productions.add(p1);

        Mockito.when(searchService.getTrendingproductionsNew(Mockito.any(String.class))).thenReturn(productions);

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        ModelAndView mav=mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav,"index");
        ModelAndViewAssert.assertModelAttributeAvailable(mav,"productions");
        ModelAndViewAssert.assertModelAttributeAvailable(mav,"selectedTimeWindow");

    }


    @Test
    public void showResultsPageNewTest() throws Exception {
        Production p1=new Production();
        List<Production> productions=new ArrayList<>();
        productions.add(p1);
        ProductionList productionList=new ProductionList();
        productionList.setResults(productions);

        Mockito.when(searchService.getAllSearchResults(Mockito.any(String.class),
                                                        Mockito.any(String.class),
                                                        Mockito.any(Integer.class),
                                                        Mockito.any(Integer.class))).thenReturn(productionList);

        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/search-results-new")
                        .param("searchQuery","dummy")
                        .param("productionType","dummy")
                        .param("searchGenre",String.valueOf(10))
                        .param("page",String.valueOf(10)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        ModelAndView mav=mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(mav,"results");

    }



}

package com.inditex;

import com.inditex.controller.PriceController;
import com.inditex.model.Brand;
import com.inditex.model.Price;
import com.inditex.repository.PriceRepository;
import com.inditex.usecase.GetPriceUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.inditex.utils.DateUtils.convert;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PriceController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {InditexApplication.class})
@ComponentScan(basePackages = {"com.inditex.usecase"})
class PriceControllerTests {

    private static final String PRICE_ENDPOINT = "/prices";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PriceRepository priceRepository;
    @MockBean
    private GetPriceUseCase getPriceUseCase;

    @Test
    void testRequest1() throws Exception {
        performAndVerify("\"2020-06-14T10:00:00.000000000+01:00", 35455l, 1l, 1, "2020-06-14T00:00:00Z", "2020-12-31T23:59:59Z", 35.50f, "EUR", 1, "ZARA");
    }

    @Test
    void testRequest2() throws Exception {
        performAndVerify("\"2020-06-14T16:00:00.000000000+01:00", 35455l, 1l, 2, "2020-06-14T00:00:00Z", "2020-06-14T18:30:00Z", 25.45f, "EUR", 1, "ZARA");
    }

    @Test
    void testRequest3() throws Exception {
        performAndVerify("\"2020-06-14T21:00:00.000000000+01:00", 35455l, 1l, 2, "2020-06-14T00:00:00Z", "2020-12-31T23:59:59Z", 35.50f, "EUR", 0 , "ZARA");
    }

    @Test
    void testRequest4() throws Exception {
        performAndVerify("\"2020-06-15T10:00:00.000000000+01:00", 35455l, 1l, 3, "2020-06-14T00:00:00Z", "2020-06-14T18:30:00Z", 25.45f, "EUR", 1, "ZARA");
    }

    @Test
    void testRequest5() throws Exception {
        performAndVerify("\"2020-06-16T21:00:00.000000000+01:00", 35455l, 1l, 4, "2020-06-14T00:00:00Z", "2020-12-31T23:59:59Z", 38.95f, "EUR", 1, "ZARA");
    }


    private void performAndVerify(String applianceDate, Long productId, Long brandId,
                                  Integer expectedPriceList, String expectedStartDate, String expectedEndDate,
                                  Float expectedPrice, String expectedCurrency, Integer expectedPriority,
                                  String brandName) throws Exception {

        String jsonPayload = createJsonPayload(productId, brandId, applianceDate);

        LocalDateTime applianceDateDateTime = convert(applianceDate);
        LocalDateTime expectedStartDateDateTime = convert(expectedStartDate);
        LocalDateTime expectedEndDateDateTime = convert(expectedEndDate);

        Brand brand = new Brand();
        brand.setName(brandName);

        when(getPriceUseCase.getPrice(brandId, productId, applianceDateDateTime))
                .thenReturn(Optional.of(new Price(brand, expectedStartDateDateTime, expectedEndDateDateTime,
                        expectedPriceList,
                        productId,
                        expectedPriority, expectedPrice, expectedCurrency)));


            mockMvc.perform(MockMvcRequestBuilders.get(PRICE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product_id").value(productId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand_name").value(brandName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price_list").value(expectedPriceList))
                .andExpect(MockMvcResultMatchers.jsonPath("$.start_date").value(expectedStartDate))
                .andExpect(MockMvcResultMatchers.jsonPath("$.end_date").value(expectedEndDate))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(expectedPrice));


    }


    private String createJsonPayload(Long productId, Long brandId, String applianceDate) {
        return "{\"product_id\":" + productId + ",\"brand_id\":" + brandId + ",\"appliance_date\":" + applianceDate + "\"}";
    }
}

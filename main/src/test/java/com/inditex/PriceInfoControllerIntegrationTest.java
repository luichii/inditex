package com.inditex;

import com.inditex.controller.PriceInfoController;
import com.inditex.entity.BrandEntity;
import com.inditex.entity.PriceEntity;
import com.inditex.repository.PriceRepository;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.inditex.utils.DateUtilsTest.convertStringToLocalDateTime;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PriceInfoController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {InditexApplication.class})
@ComponentScan(basePackages = {"com.inditex.usecase"})
class PriceInfoControllerIntegrationTest {

    private static final String PRICE_ENDPOINT = "/prices";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceRepository priceRepository;

    @Test
    void testRequest1() throws Exception {
        performAndVerify("2020-06-14T00:00:00", 35455L, 1L, 1,
            "2020-06-14T00:00:00", "2020-12-31T23:59:59",
            35.50f, "EUR", "ZARA", 1);
    }

    @Test
    void testRequest2() throws Exception {
        performAndVerify("2020-06-14T16:00:00", 35455l, 1l, 2,
            "2020-06-14T00:00:00", "2020-06-14T18:30:00", 25.45f,
            "EUR", "ZARA", 1);
    }

    @Test
    void testRequest3() throws Exception {
        performAndVerify("2020-06-14T21:00:00", 35455l, 1l, 2,
            "2020-06-14T00:00:00", "2020-12-31T23:59:59", 35.50f,
            "EUR", "ZARA", 3);
    }

    @Test
    void testRequest4() throws Exception {
        performAndVerify("2020-06-15T10:00:00", 35455l, 1l, 3,
            "2020-06-14T00:00:00", "2020-06-14T18:30:00", 25.45f,
            "EUR", "ZARA", 1);
    }

    @Test
    void testRequest5() throws Exception {
        performAndVerify("2020-06-16T21:00:00", 35455l, 1l, 4,
            "2020-06-14T00:00:00", "2020-12-31T23:59:59", 38.95f,
            "EUR", "ZARA", 1);
    }

    private void performAndVerify(String applianceDate, Long productId, Long brandId,
                                  Integer expectedPriceList, String expectedStartDate, String expectedEndDate,
                                  Float expectedPrice, String expectedCurrency, String brandName, Integer priority)
        throws Exception {

        BrandEntity brandEntity = new BrandEntity(brandId, brandName);

        PriceEntity priceEntity = createPriceEntity(brandEntity, expectedStartDate, expectedEndDate,
            expectedPriceList, productId, priority, expectedPrice, expectedCurrency);

        Optional<PriceEntity> priceEntityOptional = Optional.of(priceEntity);
        LocalDateTime applianceDateLocalDateTime = convertStringToLocalDateTime(applianceDate);

        when(priceRepository.findPricesByBrandIdAndProductIdAndDate(
            brandId, productId, applianceDateLocalDateTime)).thenReturn(priceEntityOptional);

        mockMvc.perform(MockMvcRequestBuilders.get(PRICE_ENDPOINT)
                .param("appliance_date", applianceDate)
                .param("product_id", productId.toString())
                .param("brand_id", brandId.toString())
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(result -> {
                System.out.println("Response Content: " + result.getResponse().getContentAsString());
            })
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.product_id").value(productId))
            .andExpect(MockMvcResultMatchers.jsonPath("$.brand_name").value(brandName))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price_list").value(expectedPriceList))
            .andExpect(MockMvcResultMatchers.jsonPath("$.start_date").value(expectedStartDate))
            .andExpect(MockMvcResultMatchers.jsonPath("$.end_date").value(expectedEndDate))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(expectedPrice))
            .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value(expectedCurrency));
    }

    private PriceEntity createPriceEntity(BrandEntity brandEntity, String startDate, String endDate,
                                          Integer priceList, Long productId, Integer priority,
                                          Float price, String currency) {
        BigDecimal expectedPriceBigDecimal = price == null ? null : BigDecimal.valueOf(price);
        LocalDateTime expectedStartDateLocalDateTime = LocalDateTime.parse(startDate);
        LocalDateTime expectedEndDateTimeStampLocalDateTime = LocalDateTime.parse(endDate);

        return new PriceEntity(1L, brandEntity, expectedStartDateLocalDateTime,
            expectedEndDateTimeStampLocalDateTime, priceList, productId, priority,
            expectedPriceBigDecimal, currency);
    }
}

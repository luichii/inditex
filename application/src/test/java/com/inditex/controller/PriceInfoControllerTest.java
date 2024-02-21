package com.inditex.controller;

import com.inditex.mapper.PriceMapper2;
import com.inditex.core.model.PriceInfo;
import com.inditex.openapi.samples.gen.springbootserver.model.GetPrices200ResponseDto;
import com.inditex.usecase.GetPriceUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// todo: review if have this and the same test in application (now i have it duplicated, not sure if the same code)
@ContextConfiguration(classes = {PriceInfoController.class})
@ExtendWith(SpringExtension.class)
class PriceInfoControllerTest {
    @MockBean
    private GetPriceUseCase getPriceUseCase;

    @Autowired
    private PriceInfoController priceInfoController;

    @MockBean
    private PriceMapper2 priceMapper2;


    @Test
    void testGetPrices() {
        PriceInfo priceInfo = new PriceInfo();

        when(getPriceUseCase.getPriceInfo(Mockito.any(), Mockito.any(), Mockito.any()))
            .thenReturn(priceInfo);

        when(priceMapper2.toDto(Mockito.any())).thenReturn(new GetPrices200ResponseDto());

        LocalDateTime applianceDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        Long productID = 1L;
        Long brandID = 2L;

        ResponseEntity<GetPrices200ResponseDto> actualPrices = priceInfoController.getPrices(applianceDate, productID, brandID);

        verify(priceMapper2).toDto(Mockito.any());
        verify(getPriceUseCase).getPriceInfo(Mockito.any(), Mockito.any(), Mockito.any());

        String expectedContentType = "[Content-Type:\"application/json\"]";

        // Assert that the content type header matches the expected value

        assertTrue(actualPrices.getStatusCode().is2xxSuccessful());
        assertTrue(actualPrices.hasBody());
        assertEquals(expectedContentType, actualPrices.getHeaders().toString());
    }
}

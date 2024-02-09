package com.inditex.controller;

import com.inditex.mapper.PriceMapper2;
import com.inditex.model.Price;
import com.inditex.openapi.samples.gen.springbootserver.model.GetPrices200ResponseDto;
import com.inditex.openapi.samples.gen.springbootserver.model.GetPricesRequestDto;
import com.inditex.usecase.GetPriceUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PriceController.class})
@ExtendWith(SpringExtension.class)
class PriceControllerTest {
    @MockBean
    private GetPriceUseCase getPriceUseCase;

    @Autowired
    private PriceController priceController;

    @MockBean
    private PriceMapper2 priceMapper2;


    @Test
    void testGetPrices() {
        Optional<Price> ofResult = Optional.of(new Price());
        when(getPriceUseCase.getPrice(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(ofResult);
        when(priceMapper2.toDto(Mockito.any())).thenReturn(new GetPrices200ResponseDto());

        GetPricesRequestDto getPricesRequestDto = new GetPricesRequestDto();
        getPricesRequestDto.applianceDate(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        ResponseEntity<GetPrices200ResponseDto> actualPrices = priceController.getPrices(getPricesRequestDto);
        verify(priceMapper2).toDto(Mockito.any());
        verify(getPriceUseCase).getPrice(Mockito.any(), Mockito.any(), Mockito.any());
        assertEquals(200, actualPrices.getStatusCodeValue());
        assertTrue(actualPrices.hasBody());
        assertTrue(actualPrices.getHeaders().isEmpty());
    }

    @Test
    void testGetPrices2() {
        Optional<Price> emptyResult = Optional.empty();
        when(getPriceUseCase.getPrice(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(emptyResult);

        GetPricesRequestDto getPricesRequestDto = new GetPricesRequestDto();
        getPricesRequestDto.applianceDate(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        ResponseEntity<GetPrices200ResponseDto> actualPrices = priceController.getPrices(getPricesRequestDto);
        verify(getPriceUseCase).getPrice(Mockito.<Long>any(), Mockito.any(), Mockito.any());
        assertNull(actualPrices.getBody());
        assertEquals(404, actualPrices.getStatusCodeValue());
        assertTrue(actualPrices.getHeaders().isEmpty());
    }
}

package com.inditex.controller;

import com.inditex.mapper.PriceMapper2;
import com.inditex.model.Price;
import com.inditex.openapi.samples.gen.springbootserver.api.PricesApi;
import com.inditex.openapi.samples.gen.springbootserver.model.GetPrices200ResponseDto;
import com.inditex.openapi.samples.gen.springbootserver.model.GetPricesRequestDto;
import com.inditex.usecase.GetPriceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PriceController implements PricesApi {

    private GetPriceUseCase getPriceUseCase;

    private PriceMapper2 priceMapper2;

    public PriceController(GetPriceUseCase getPriceUseCase, PriceMapper2 priceMapper2) {
        this.getPriceUseCase = getPriceUseCase;
        this.priceMapper2 = priceMapper2;

    }

    @Override
    public ResponseEntity<GetPrices200ResponseDto> getPrices(GetPricesRequestDto getPricesRequestDto) {


        Optional<Price> priceOptional = getPriceUseCase.getPrice(getPricesRequestDto.getBrandId(),
                getPricesRequestDto.getProductId(), getPricesRequestDto.getApplianceDate().toLocalDateTime());

        if (priceOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        GetPrices200ResponseDto response = priceMapper2.toDto(priceOptional.get());


        return ResponseEntity.ok(response);
    }
}
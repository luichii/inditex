package com.inditex.controller;

import com.inditex.core.model.PriceInfo;
import com.inditex.mapper.PriceMapper2;
import com.inditex.openapi.samples.gen.springbootserver.api.PricesApi;
import com.inditex.openapi.samples.gen.springbootserver.model.GetPrices200ResponseDto;
import com.inditex.usecase.GetPriceUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController

public class PriceInfoController implements PricesApi {

    private GetPriceUseCase getPriceUseCase;

    private PriceMapper2 priceMapper2;

    public PriceInfoController(GetPriceUseCase getPriceUseCase, PriceMapper2 priceMapper2) {
        this.getPriceUseCase = getPriceUseCase;
        this.priceMapper2 = priceMapper2;
    }

    @Override
    public ResponseEntity<GetPrices200ResponseDto> getPrices(@NotNull @Parameter(name = "appliance_date",
        description = "Appliance date of rating", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "appliance_date", required = true) LocalDateTime applianceDate,
                                                             @NotNull @Parameter(name = "product_id", description = "Product identifier", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "product_id", required = true) Long productId,
                                                             @NotNull @Parameter(name = "brand_id", description = "Brand identifier", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "brand_id", required = true) Long brandId) {

        GetPrices200ResponseDto response = getGetPrices200ResponseDto(applianceDate, productId, brandId);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private GetPrices200ResponseDto getGetPrices200ResponseDto(LocalDateTime applianceDate, Long productId,
                                                               Long brandId) {

        PriceInfo priceInfo = getPriceUseCase.getPriceInfo(brandId, productId, applianceDate);

        return priceMapper2.toDto(priceInfo);
    }
}

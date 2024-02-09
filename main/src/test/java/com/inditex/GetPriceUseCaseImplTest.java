package com.inditex;

import com.inditex.dto.PriceDTO;
import com.inditex.mapper.PriceMapper;
import com.inditex.mapper.PriceMapper2;
import com.inditex.model.Brand;
import com.inditex.model.Price;
import com.inditex.port.PriceRepositoryPort;
import com.inditex.usecase.impl.GetPriceUseCaseImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GetPriceUseCaseImplTest {

    @InjectMocks
    private GetPriceUseCaseImpl getPriceUseCase;

    @Mock
    private PriceMapper priceMapper;

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @Mock
    private PriceMapper2 priceMapper2;

    @Test
    void testGetPrice() {
        Mockito.when(priceRepositoryPort.findPricesByBrandIdAndProductIdAndDate(
                        Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<>());

        Optional<Price> actualPrice = getPriceUseCase.getPrice(1L, 1L, LocalDate.of(1970, 1, 1).atStartOfDay());

        Mockito.verify(priceRepositoryPort).findPricesByBrandIdAndProductIdAndDate(
                Mockito.any(), Mockito.any(), Mockito.any());

        Assertions.assertFalse(actualPrice.isPresent());
    }


    @Test
    void testGetPrice2() {

        List<PriceDTO> prices = new ArrayList<>();
        Long brandId = 1L;
        Long productId = 12345L;
        Integer priceList = 1;
        LocalDateTime startDate = LocalDateTime.of(2024, 2, 10, 8, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 2, 20, 18, 0);
        Float price = 25.99f;
        Integer priority = 1;
        String currency = "USD";

        PriceDTO priceDTO = new PriceDTO(brandId, productId, priceList, startDate, endDate, price, currency, priority);

        prices.add(priceDTO);

        Mockito.when(priceRepositoryPort.findPricesByBrandIdAndProductIdAndDate(
                        Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(prices);

        Brand brand = new Brand();

        Price priceModel = new Price(brand, startDate, endDate, priceList, productId, priority,  price, currency);

        Mockito.when(priceMapper2.toPrice(priceDTO)).thenReturn(priceModel);

        Optional<Price> actualPrice = getPriceUseCase.getPrice(1L, 1L, LocalDate.of(1970, 1, 1).atStartOfDay());


        Mockito.verify(priceRepositoryPort).findPricesByBrandIdAndProductIdAndDate(
                Mockito.any(), Mockito.any(), Mockito.any());

        Assertions.assertTrue(actualPrice.isPresent());
    }
}

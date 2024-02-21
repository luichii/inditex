package com.inditex.usecase;

import com.inditex.dto.PriceDTO;
import com.inditex.mapper.PriceMapper2;
import com.inditex.core.model.PriceInfo;
import com.inditex.port.PriceRepositoryPort;
import com.inditex.usecase.impl.GetPriceUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPriceUseCaseImplTest {

    @InjectMocks
    private GetPriceUseCaseImpl getPriceUseCase;

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @Mock
    private PriceMapper2 priceMapper2;

    @Test
    void getPriceInfo_WhenPriceFound_ReturnsPriceInfo() {
        // Arrange
        Long brandId = 1L;
        Long productId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();
        PriceDTO priceDTO = mock(PriceDTO.class); // Assuming PriceDTO can be mocked
        PriceInfo expectedPriceInfo = mock(PriceInfo.class); // Assuming PriceInfo can be mocked

        when(priceRepositoryPort.findPricesByBrandIdAndProductIdAndDate(brandId, productId, applicationDate))
            .thenReturn(priceDTO);
        when(priceMapper2.toPrice(priceDTO)).thenReturn(expectedPriceInfo);

        // Act
        PriceInfo actualPriceInfo = getPriceUseCase.getPriceInfo(brandId, productId, applicationDate);

        // Assert
        assertNotNull(actualPriceInfo);
        assertEquals(expectedPriceInfo, actualPriceInfo);
        verify(priceRepositoryPort).findPricesByBrandIdAndProductIdAndDate(brandId, productId, applicationDate);
        verify(priceMapper2).toPrice(priceDTO);
    }

    @Test
    void getPriceInfo_WhenNoPriceFound_ReturnsNull() {
        // Arrange
        Long brandId = 1L;
        Long productId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(priceRepositoryPort.findPricesByBrandIdAndProductIdAndDate(brandId, productId, applicationDate))
            .thenReturn(null);

        // Act
        PriceInfo actualPriceInfo = getPriceUseCase.getPriceInfo(brandId, productId, applicationDate);

        // Assert
        assertNull(actualPriceInfo);
    }

    @Test
    void getPriceInfo_WithInvalidInputParameters_ReturnsNull() {
        // Act & Assert for null inputs
        assertNull(getPriceUseCase.getPriceInfo(null, null, null));

        // Act & Assert for invalid brandId and productId
        assertNull(getPriceUseCase.getPriceInfo(-1L, -1L, LocalDateTime.now()));
    }
}

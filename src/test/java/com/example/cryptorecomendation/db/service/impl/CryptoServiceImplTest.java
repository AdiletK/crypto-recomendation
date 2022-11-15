package com.example.cryptorecomendation.db.service.impl;

import com.example.cryptorecomendation.db.entity.CryptoDetail;
import com.example.cryptorecomendation.db.repo.CryptoDetailRepo;
import com.example.cryptorecomendation.model.BasicCryptoInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CryptoServiceImplTest {
    private final String CRYPTO_NAME = "BTC";

    @Mock
    private CryptoDetailRepo detailRepo;

    @InjectMocks
    private CryptoServiceImpl cryptoService;

    @Test
    void testGetBasicInfo() {
        when(detailRepo.findOldestCrypto(CRYPTO_NAME))
            .thenReturn(new CryptoDetail());
        when(detailRepo.findNewestCrypto(CRYPTO_NAME))
            .thenReturn(new CryptoDetail());
        when(detailRepo.findMinCrypto(CRYPTO_NAME))
            .thenReturn(new CryptoDetail());
        when(detailRepo.findMaxCrypto(CRYPTO_NAME))
            .thenReturn(new CryptoDetail());

        BasicCryptoInfo basicInfo = cryptoService.getBasicInfo(CRYPTO_NAME);

        assertNotNull(basicInfo.getMax());
        assertNotNull(basicInfo.getMin());
        assertNotNull(basicInfo.getNewest());
        assertNotNull(basicInfo.getOldest());
    }

    @Test
    void isExist() {
        when(detailRepo.existsByCryptoName(CRYPTO_NAME))
            .thenReturn(true);

        boolean exist = cryptoService.isExist(CRYPTO_NAME);

        assertTrue(exist);
    }

    @Test
    void testFindAllByRangeDate() {
        Long startDate = 1641009600000L;
        Long endDate = 1641011600000L;
        when(detailRepo.findAllByRangeDate(startDate, endDate))
            .thenReturn(getSampleData());
        List<CryptoDetail> rangeDate = cryptoService.findAllByRangeDate(startDate, endDate);

        assertEquals(1, rangeDate.size());
        assertEquals(CRYPTO_NAME, rangeDate.get(0).getCryptoName());
    }

    @Test
    void testFindAllByRangeData() {
        Double min = 10.0;
        Double max = 30.0;
        when(detailRepo.findAllByRangeData(min, max))
            .thenReturn(getSampleData());
        List<CryptoDetail> rangeDate = cryptoService.findAllByRangeData(min, max);

        assertEquals(1, rangeDate.size());
        assertEquals(CRYPTO_NAME, rangeDate.get(0).getCryptoName());
    }

    private List<CryptoDetail> getSampleData() {
        CryptoDetail detail = new CryptoDetail();
        detail.setCryptoName(CRYPTO_NAME);
        detail.setValue(23.23);
        detail.setDateTime(1641009600000L);
        detail.setId(1L);
        return Collections.singletonList(detail);
    }

}
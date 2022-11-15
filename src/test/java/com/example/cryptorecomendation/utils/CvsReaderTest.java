package com.example.cryptorecomendation.utils;

import com.example.cryptorecomendation.model.CryptoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class CvsReaderTest {
    @Autowired
    private ResourcePatternResolver resourcePatternResolver;
    @Spy
    private CvsReader reader;


    @DisplayName("Testing parse from cvs file!")
    @Test
    void parseFromResource() {
        int expectedSize = 80;
        Resource resource = resourcePatternResolver.getResource("classpath:/XRP_values.csv");
        List<CryptoDto> parsedData = reader.parse(resource);

        Assertions.assertEquals(expectedSize, parsedData.size());
    }
}
package com.example.cryptorecomendation.utils;

import com.example.cryptorecomendation.db.service.CryptoService;
import com.example.cryptorecomendation.model.CryptoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitializerValues implements CommandLineRunner {
    private final CvsReader cvsReader;
    private final ResourcePatternResolver resourcePatternResolver;

    private final CryptoService cryptoService;

    @Override
    public void run(String... args) throws Exception {
        Resource[] resources = resourcePatternResolver.getResources("classpath:/samples/*");
        for (Resource resource : resources) {
            List<CryptoDto> dtos = cvsReader.parse(resource);
            cryptoService.save(dtos);
        }
    }
}

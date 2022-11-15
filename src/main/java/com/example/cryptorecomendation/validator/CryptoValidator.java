package com.example.cryptorecomendation.validator;

import com.example.cryptorecomendation.db.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class CryptoValidator {
    private final CryptoService cryptoService;

    public void checkIsSupported(String cryptoName) {
        boolean isExist = cryptoService.isExist(cryptoName);
        if (!isExist) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This crypto currency not supported yet, we'll add as soon as possible!");
        }
    }
}

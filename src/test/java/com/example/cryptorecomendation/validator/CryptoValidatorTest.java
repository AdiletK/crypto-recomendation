package com.example.cryptorecomendation.validator;

import com.example.cryptorecomendation.db.service.CryptoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CryptoValidatorTest {
    @Mock
    private CryptoService cryptoService;

    @InjectMocks
    private CryptoValidator validator;

    @Test
    void successCaseValidation() {
        String cryptoName = "BTC";
        Mockito.when(cryptoService.isExist(cryptoName))
            .thenReturn(true);

        assertDoesNotThrow(() -> validator.checkIsSupported(cryptoName));
    }

    @Test
    void caseWhenValidationThrowException() {
        String cryptoName = "BTC";
        Mockito.when(cryptoService.isExist(cryptoName))
            .thenReturn(false);

        assertThrows(ResponseStatusException.class,
            () -> validator.checkIsSupported(cryptoName));
    }
}
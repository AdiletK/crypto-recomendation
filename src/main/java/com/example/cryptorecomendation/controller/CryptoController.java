package com.example.cryptorecomendation.controller;

import com.example.cryptorecomendation.db.entity.CryptoDetail;
import com.example.cryptorecomendation.db.service.CryptoService;
import com.example.cryptorecomendation.model.BasicCryptoInfo;
import com.example.cryptorecomendation.model.CryptoDto;
import com.example.cryptorecomendation.utils.CvsReader;
import com.example.cryptorecomendation.validator.CryptoValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("crypto/")
public class CryptoController {
    private final CryptoService cryptoService;
    private final CryptoValidator validator;

    private final CvsReader reader;

    @Operation(summary = "Get the oldest/newest/min/max values by crypto name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the oldest/newest/min/max values for a requested crypto!",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = BasicCryptoInfo.class))}),
        @ApiResponse(responseCode = "404", description = "Crypto not found",
            content = @Content)})
    @GetMapping("{crypto_name}")
    public BasicCryptoInfo getBasicInfo(@PathVariable("crypto_name") String cryptoName) {
        validator.checkIsSupported(cryptoName);
        return cryptoService.getBasicInfo(cryptoName);
    }

    @Operation(summary = "Getting filtered by range data by value or date")
    @ApiResponse(responseCode = "200", description = "Return a descending sorted list of all the cryptos!",
        content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = ArrayList.class))})
    @GetMapping("/filter")
    public List<CryptoDetail> findAllByRange(@RequestParam(required = false) Double min,
                                             @RequestParam(required = false) Double max,
                                             @RequestParam(required = false) Long start,
                                             @RequestParam(required = false) Long end) {
        if (min != null && max != null) {
            return cryptoService.findAllByRangeData(min, max);
        }
        if (start != null && end != null) {
            return cryptoService.findAllByRangeDate(start, end);
        }

        return Collections.emptyList();
    }

    @Operation(summary = "Upload method to add new crypto data")
    @ApiResponse(responseCode = "200")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadNewCrypto(@RequestPart MultipartFile file) {
        List<CryptoDto> dtos = reader.parse(file);
        cryptoService.save(dtos);
        return ResponseEntity.ok().build();
    }


}

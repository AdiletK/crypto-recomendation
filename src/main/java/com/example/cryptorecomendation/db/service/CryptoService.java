package com.example.cryptorecomendation.db.service;

import com.example.cryptorecomendation.db.entity.CryptoDetail;
import com.example.cryptorecomendation.model.BasicCryptoInfo;
import com.example.cryptorecomendation.model.CryptoDto;

import java.util.List;

public interface CryptoService {
    BasicCryptoInfo getBasicInfo(String cryptoName);

    BasicCryptoInfo getBasicInfo(String cryptoName, Long start, Long end);

    boolean isExist(String cryptoName);

    List<CryptoDetail> findAllByRangeData(Double min, Double max);

    List<CryptoDetail> findAllByRangeDate(Long start, Long end);

    void save(List<CryptoDto> cryptoDtoList);
}

package com.example.cryptorecomendation.db.service.impl;

import com.example.cryptorecomendation.db.entity.CryptoDetail;
import com.example.cryptorecomendation.db.repo.CryptoDetailRepo;
import com.example.cryptorecomendation.db.service.CryptoService;
import com.example.cryptorecomendation.model.BasicCryptoInfo;
import com.example.cryptorecomendation.model.CryptoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoServiceImpl implements CryptoService {
    private final CryptoDetailRepo detailRepo;

    @Override
    public BasicCryptoInfo getBasicInfo(String cryptoName) {
        return BasicCryptoInfo.builder()
            .oldest(detailRepo.findOldestCrypto(cryptoName))
            .newest(detailRepo.findNewestCrypto(cryptoName))
            .max(detailRepo.findMaxCrypto(cryptoName))
            .min(detailRepo.findMinCrypto(cryptoName)).build();
    }

    @Override
    public BasicCryptoInfo getBasicInfo(String cryptoName, Long start, Long end) {
        List<CryptoDetail> all = detailRepo.findAllByRangeDate(start, end);
        List<CryptoDetail> cryptoDetailList = all.stream()
            .filter(el -> el.getCryptoName().equals(cryptoName))
            .collect(Collectors.toList());

        BasicCryptoInfo info = new BasicCryptoInfo();

        cryptoDetailList.stream()
            .min(Comparator.comparingDouble(CryptoDetail::getValue))
            .ifPresent(info::setMin);

        cryptoDetailList.stream()
            .max(Comparator.comparingDouble(CryptoDetail::getValue))
            .ifPresent(info::setMax);

        cryptoDetailList.stream()
            .min(Comparator.comparingLong(CryptoDetail::getDateTime))
            .ifPresent(info::setOldest);

        cryptoDetailList.stream()
            .max(Comparator.comparingLong(CryptoDetail::getDateTime))
            .ifPresent(info::setNewest);

        return info;
    }

    @Override
    public boolean isExist(String cryptoName) {
        return detailRepo.existsByCryptoName(cryptoName);
    }

    @Override
    public List<CryptoDetail> findAllByRangeData(Double min, Double max) {
        return detailRepo.findAllByRangeData(min, max);
    }

    @Override
    public List<CryptoDetail> findAllByRangeDate(Long start, Long end) {
        return detailRepo.findAllByRangeDate(start, end);
    }


    @Override
    public void save(List<CryptoDto> cryptoDtos) {
        for (CryptoDto dto : cryptoDtos) {
            save(dto);
        }
    }

    private void save(CryptoDto dto) {
        CryptoDetail detail = new CryptoDetail();
        detail.setCryptoName(dto.getName());
        detail.setValue(dto.getValue());
        detail.setDateTime(dto.getTimeStamp());
        detailRepo.save(detail);
    }
}

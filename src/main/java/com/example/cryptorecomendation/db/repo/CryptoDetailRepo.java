package com.example.cryptorecomendation.db.repo;

import com.example.cryptorecomendation.db.entity.CryptoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CryptoDetailRepo extends JpaRepository<CryptoDetail, Long> {

    @Query(value = "select * from crypto_detail where crypto_name = :name order by date_time asc limit 1", nativeQuery = true)
    CryptoDetail findOldestCrypto(String name);

    @Query(value = "select * from crypto_detail where crypto_name = :name order by date_time desc limit 1", nativeQuery = true)
    CryptoDetail findNewestCrypto(String name);

    @Query(value = "select * from crypto_detail where crypto_name = :name order by data asc limit 1", nativeQuery = true)
    CryptoDetail findMinCrypto(String name);

    @Query(value = "select * from crypto_detail where crypto_name = :name order by data desc limit 1", nativeQuery = true)
    CryptoDetail findMaxCrypto(String name);

    @Query(value = "select * from crypto_detail where data between :min and :max order by data desc", nativeQuery = true)
    List<CryptoDetail> findAllByRangeData(Double min, Double max);

    @Query(value = "select * from crypto_detail where date_time between :start and :end order by date_time desc", nativeQuery = true)
    List<CryptoDetail> findAllByRangeDate(Long start, Long end);
    boolean existsByCryptoName(String name);
}

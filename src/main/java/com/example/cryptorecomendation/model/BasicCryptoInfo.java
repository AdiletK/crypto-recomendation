package com.example.cryptorecomendation.model;

import com.example.cryptorecomendation.db.entity.CryptoDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicCryptoInfo {
    private CryptoDetail oldest;
    private CryptoDetail newest;
    private CryptoDetail min;
    private CryptoDetail max;
}

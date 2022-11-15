package com.example.cryptorecomendation.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class CryptoDto {
    @CsvBindByPosition(position = 0)
    private Long timeStamp;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindByPosition(position = 2)
    private Double value;
}

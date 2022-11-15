package com.example.cryptorecomendation.utils;

import com.example.cryptorecomendation.model.CryptoDto;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class CvsReader {
    public List<CryptoDto> parse(Resource resource) {
        try {
            return readData(resource.getFile());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't read file!");
        }
    }

    public List<CryptoDto> parse(MultipartFile multipartFile) {
        File file = convertToFile(multipartFile);
        return readData(file);
    }

    public List<CryptoDto> readData(File file) {
        try {
            FileReader filereader = new FileReader(file);

            return new CsvToBeanBuilder<CryptoDto>(filereader)
                .withSkipLines(1)
                .withType(CryptoDto.class)
                .build()
                .parse();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public File convertToFile(MultipartFile file) {
        try {
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return convFile;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't convert to file!");
        }
    }
}

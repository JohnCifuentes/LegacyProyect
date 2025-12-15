package com.legacybridge.service.implement;

import com.legacybridge.config.Settings;
import com.legacybridge.dto.TransactionXmlDTO;
import com.legacybridge.dto.TransactionResponseXmlDTO;
import com.legacybridge.dto.caregorydetail.ReadCategoryDetailDTO;
import com.legacybridge.service.LegacyApiService;
import com.legacybridge.config.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LegacyApiServiceImplement implements LegacyApiService {
    private static final Random RANDOM = new Random();
    private static final Settings setting = new Settings();

    private final CategoryDetailServiceImplement categoryDetailService;

    @Override
    public TransactionResponseXmlDTO getTransactions() {
        List<TransactionXmlDTO> transactionDTO = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            transactionDTO.add(new TransactionXmlDTO("txn_" + i, this.getDescriptionRandom(), this.getAmountRandom(), this.getCurrencyRandom(), this.getDateRandom()));
        }
        return new TransactionResponseXmlDTO(transactionDTO);
    }

    private String randomPart() {
        return RANDOM.nextBoolean()
                ? setting.getWords().get(RANDOM.nextInt(setting.getWords().size()))
                : String.valueOf(RANDOM.nextInt(9999));
    }

    private String randomSeparator() {
        return setting.getSeparators().get(RANDOM.nextInt(setting.getSeparators().size()));
    }
    
    public String generate(String keyword) {
        int totalParts = RANDOM.nextInt(3) + 2;
        int keywordPosition = RANDOM.nextInt(totalParts);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < totalParts; i++) {
            if (i == keywordPosition) {
                sb.append(keyword);
            } else {
                sb.append(randomPart());
            }
            if (i < totalParts - 1) {
                sb.append(randomSeparator());
            }
        }

        return sb.toString().trim();
    }

    public String getDescriptionRandom(){
        List<ReadCategoryDetailDTO> readCategoryDetailDTOS = categoryDetailService.getAllCategoryDetails();
        return generate(readCategoryDetailDTOS.get(RANDOM.nextInt(readCategoryDetailDTOS.size())).value());
    }

    public String getAmountRandom(){
        int optionRandom = RANDOM.nextInt(10) + 1;
        int amountRandom = RANDOM.nextInt(10000000);
        if(optionRandom == 3){
            return Currency.EURO.getSymbol() + amountRandom;
        }
        if(optionRandom == 6){
            return Currency.DOLLAR.getSymbol() + amountRandom;
        }
        if(optionRandom == 9){
            return Currency.PESO.getSymbol() + amountRandom;
        }
        return String.valueOf(amountRandom);
    }

    public String getCurrencyRandom(){
        int numberRandom = RANDOM.nextInt(3) + 1;
        if(numberRandom == 1){
            return Currency.EURO.getCurrency();
        }
        if(numberRandom == 2){
            return Currency.DOLLAR.getCurrency();
        }
        return Currency.PESO.getCurrency();
    }

    public String getDateRandom(){
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = minDay + RANDOM.nextInt((int) (maxDay - minDay));

        LocalDate dateAl = LocalDate.ofEpochDay(randomDay);

        int indiceFormato = RANDOM.nextInt(setting.getFormatters().size());
        DateTimeFormatter formatter = setting.getFormatters().get(indiceFormato);

        return dateAl.format(formatter);
    }

}

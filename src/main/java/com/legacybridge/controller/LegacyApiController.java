package com.legacybridge.controller;

import com.legacybridge.dto.TransactionResponseXmlDTO;
import com.legacybridge.service.LegacyApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/legacy")
@CrossOrigin(origins = "*")
public class LegacyApiController {
    private final LegacyApiService legacyService;

    @GetMapping(
            value = "/transactions",
            produces = MediaType.APPLICATION_XML_VALUE
    )
    public TransactionResponseXmlDTO getTransactions() {
        return legacyService.getTransactions();
    }

}

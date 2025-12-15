package com.legacybridge.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Transaction")
public record TransactionXmlDTO(
    @JacksonXmlProperty(localName = "txn_id")
    String txn_id,
    @JacksonXmlProperty(localName = "description")
    String description,
    @JacksonXmlProperty(localName = "amount")
    String amount,
    @JacksonXmlProperty(localName = "currency")
    String currency,
    @JacksonXmlProperty(localName = "date")
    String date
) {
}

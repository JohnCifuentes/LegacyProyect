package com.legacybridge.mapper;

import com.legacybridge.dto.transaction.CreateTransactionDTO;
import com.legacybridge.dto.transaction.DeleteTransactionDTO;
import com.legacybridge.dto.transaction.ReadTransactionDTO;
import com.legacybridge.dto.transaction.UpdateTransactionDTO;
import com.legacybridge.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    /**
     * CREATE
     */
    Transaction toEntity(CreateTransactionDTO transactionDTO);

    /**
     * UPDATE
     */
    void updateEntityFromDTO(UpdateTransactionDTO transactionDTO, @MappingTarget Transaction transaction);

    /**
     * DELETE/INACTIVE
     */
    void deleteEntityFromDTO(DeleteTransactionDTO transactionDTO, @MappingTarget Transaction transaction);

    /**
     * GET
     */
    ReadTransactionDTO toDTO(Transaction transaction);

}

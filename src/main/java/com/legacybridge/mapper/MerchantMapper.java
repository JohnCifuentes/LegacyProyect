package com.legacybridge.mapper;

import com.legacybridge.dto.merchant.CreateMerchantDTO;
import com.legacybridge.dto.merchant.DeleteMerchantDTO;
import com.legacybridge.dto.merchant.ReadMerchantDTO;
import com.legacybridge.dto.merchant.UpdateMerchantDTO;
import com.legacybridge.model.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    /**
     * CREATE
     */
    Merchant toEntity(CreateMerchantDTO merchantDTO);

    /**
     * UPDATE
     */
    void updateEntityFromDTO(UpdateMerchantDTO merchantDTO, @MappingTarget Merchant merchant);

    /**
     * DELETE/INACTIVE
     */
    void deleteEntityFromDTO(DeleteMerchantDTO merchantDTO, @MappingTarget Merchant merchant);

    /**
     * GET
     */
    ReadMerchantDTO toDTO(Merchant merchant);

}

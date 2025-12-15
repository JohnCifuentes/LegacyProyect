package com.legacybridge.repository;

import com.legacybridge.dto.merchant.ReadMerchantDTO;
import com.legacybridge.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    List<ReadMerchantDTO> findByOrderByMerchantIdAsc();

}

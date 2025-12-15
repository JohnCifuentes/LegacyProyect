package com.legacybridge.repository;

import com.legacybridge.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByOrderByCategoryIdAsc();

}

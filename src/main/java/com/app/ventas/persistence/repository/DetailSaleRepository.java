package com.app.ventas.persistence.repository;

import com.app.ventas.persistence.entity.DetailSale;
import com.app.ventas.persistence.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailSaleRepository extends JpaRepository<DetailSale, Long> {
}

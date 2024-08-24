package com.algaworks.awpag.domain.repository;

import com.algaworks.awpag.domain.model.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFinanceRepository extends JpaRepository<Finance, Long> {
}

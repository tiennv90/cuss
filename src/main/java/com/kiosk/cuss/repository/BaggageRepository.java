package com.kiosk.cuss.repository;

import com.kiosk.cuss.entity.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BaggageRepository extends JpaRepository<Baggage, Long>, JpaSpecificationExecutor<Baggage> {

    @Modifying
    @Transactional
    @Query("UPDATE Baggage b SET b.status = :status WHERE b.id IN :baggageIds")
    void updateStatus(List<Long> baggageIds, String status);

}

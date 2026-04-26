package com.kiosk.cuss.repository;

import com.kiosk.cuss.entity.CheckInLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInLogRepository extends JpaRepository<CheckInLog, Long> {

}

package com.iozturk.service_monitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckResultRepository extends JpaRepository<CheckResult, Long> {

    @Query("SELECT c FROM CheckResult c WHERE c.checkedAt = (SELECT MAX(c2.checkedAt) FROM CheckResult c2 WHERE c2.url = c.url)")
    List<CheckResult> findLatestForEachUrl();
}
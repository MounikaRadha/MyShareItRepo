package com.m0wn1ka.MyShareIt.repository;

import com.m0wn1ka.MyShareIt.models.RegularWords;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularWordsRepository extends JpaRepository<RegularWords,Long> {
    RegularWords findFirstByAlreadyUsedStatusFalse();

    RegularWords findByRegularWord(String regularWord);
    @Modifying
    @Transactional
    @Query("UPDATE  RegularWords row1 set row1.alreadyUsedStatus=false ")
    void updateAllByAlreadyUsedStatusSetToTrue();
}

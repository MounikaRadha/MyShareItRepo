package com.m0wn1ka.MyShareIt.repository;

import com.m0wn1ka.MyShareIt.models.FileUploads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadsRepository extends JpaRepository<FileUploads,Long> {
    FileUploads findByGenericName(String regularWord);
    void deleteAll();
}

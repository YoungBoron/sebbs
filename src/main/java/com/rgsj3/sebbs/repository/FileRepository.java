package com.rgsj3.sebbs.repository;

import com.rgsj3.sebbs.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}

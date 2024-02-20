package com.minashkin.project.repository;

import com.minashkin.project.model.entyti.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepo extends JpaRepository<Cat, Long> {
}

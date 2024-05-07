package com.jmvillel.demo.spacecraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jmvillel.demo.spacecraft.domain.SpaceCraft;

public interface SpaceCraftRepository extends JpaRepository<SpaceCraft, Long> {

}

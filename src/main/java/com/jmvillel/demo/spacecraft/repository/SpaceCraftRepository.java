package com.jmvillel.demo.spacecraft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jmvillel.demo.spacecraft.domain.SpaceCraft;

public interface SpaceCraftRepository extends JpaRepository<SpaceCraft, Long> {

	List<SpaceCraft> findAllByNameContainingIgnoreCase(String name);

}

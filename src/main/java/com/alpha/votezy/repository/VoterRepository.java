package com.alpha.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.votezy.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long>{
	boolean existsByEmail(String email);
}

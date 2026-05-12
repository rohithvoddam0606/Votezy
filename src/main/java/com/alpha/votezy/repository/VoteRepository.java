package com.alpha.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.votezy.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{

}

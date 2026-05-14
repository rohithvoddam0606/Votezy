package com.alpha.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.votezy.entity.Candidate;
import com.alpha.votezy.entity.Vote;
import com.alpha.votezy.entity.Voter;
import com.alpha.votezy.exception.DuplicateResourceException;
import com.alpha.votezy.exception.ResourceNotFoundException;
import com.alpha.votezy.repository.CandidateRepository;
import com.alpha.votezy.repository.VoterRepository;

import jakarta.transaction.Transactional;

@Service
public class VoterService {
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;

	@Autowired
	public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
	}

	public Voter registerVoter(Voter voter) {
		if (voterRepository.existsByEmail(voter.getEmail())) {
			throw new DuplicateResourceException("Voter already exists");
		}
		return voterRepository.save(voter);
	}

	public List<Voter> getAllVoters() {
		return voterRepository.findAll();
	}

	public Voter getVoterById(long id) {
		Voter voter = voterRepository.findById(id).orElseThrow(null);
		if (voter == null) {
			throw new ResourceNotFoundException("Voter not found with id: " + id);
		}
		return voter;
	}
	
	public Voter updateVoter(Long id, Voter updatedVoter) {
		Voter existingVoter = voterRepository.findById(id).orElseThrow(null);
		if (existingVoter == null) {
			throw new ResourceNotFoundException("Voter not found with id: " + id);
		}
		if(existingVoter.getName()!=null) {
		existingVoter.setName(updatedVoter.getName());
		}
		if(existingVoter.getEmail()!=null) {
		existingVoter.setEmail(updatedVoter.getEmail());
		}
		return voterRepository.save(existingVoter);
	}
	
	@Transactional
	public void deleteVoter(Long id) {
		Voter voter = voterRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Voter not found with id: " + id));
		Vote vote = voter.getVote();
		if (vote != null) {
			Candidate candidate = vote.getCandidate();
			candidate.setVoteCount(candidate.getVoteCount() - 1);
			candidateRepository.save(candidate);
		}
		voterRepository.delete(voter);
	}
}

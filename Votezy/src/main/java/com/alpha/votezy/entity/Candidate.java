package com.alpha.votezy.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Candidate {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Party is required")
	private String party;
	
	private int voteCount=0;
	
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
	private List<Vote> vote;
}

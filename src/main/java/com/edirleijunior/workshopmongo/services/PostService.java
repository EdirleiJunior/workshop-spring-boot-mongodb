package com.edirleijunior.workshopmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edirleijunior.workshopmongo.domain.Post;
import com.edirleijunior.workshopmongo.repository.PostRepository;
import com.edirleijunior.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Post user = repo.findById(id).orElse(null);
		if (user == null) {
			throw new ObjectNotFoundException("Obejto nao encontrado");
		}
		return user;
	}
	
	
}

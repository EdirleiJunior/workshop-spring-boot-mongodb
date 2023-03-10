package com.edirleijunior.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.edirleijunior.workshopmongo.domain.Post;
import com.edirleijunior.workshopmongo.domain.User;
import com.edirleijunior.workshopmongo.dto.AuthorDTO;
import com.edirleijunior.workshopmongo.dto.CommentDTO;
import com.edirleijunior.workshopmongo.repository.PostRepository;
import com.edirleijunior.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Greener", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2023"), "Partiu estudos!", "Vou estudar Spring Boot", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2023"), "Bom dia!", "Ainda não dormi, passei a noite estudando.",  new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Bons estudos!", sdf.parse("21/03/2023"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2023"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um otimo dia!", sdf.parse("23/03/2023"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1 , post2));
		userRepository.save(maria);
	}
}

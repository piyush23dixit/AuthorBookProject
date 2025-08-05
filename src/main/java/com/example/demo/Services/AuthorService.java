package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Author;
import com.example.demo.Entities.Book;
import com.example.demo.Repository.AuthorRepository;

@Service
@Component
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public List<Author> addAllAuthors(List<Author> ad){
		return authorRepository.saveAll(ad);
	}
	
	public Author addAuthor(Author a) {
		return authorRepository.save(a);
	}
	
	public List<Author> getAllAuthors(){
		return authorRepository.findAll();
	}
	
	public Page<Author> getPageAuthors(Pageable pageable){
		return authorRepository.findAll(pageable);
	}
    
	public Author getAuthorById(String id) {
		return authorRepository.findById(id).orElse(null);
	}
	
	public void updateAuthor(Author ad,String id) {
		ad.setAuthorId(id);
		authorRepository.save(ad);
	}
	
	public void deleteAuthor(String id) {
		authorRepository.deleteById(id);
	}
	

}






package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Entities.Author;

public interface AuthorRepository extends MongoRepository<Author, String>{
   
	 public Optional<Author> findById(String id);
}

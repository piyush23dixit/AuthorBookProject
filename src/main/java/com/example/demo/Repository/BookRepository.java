package com.example.demo.Repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Entities.Book;

public interface BookRepository extends MongoRepository<Book, String>{
	public Optional<Book> findById(String id);

}

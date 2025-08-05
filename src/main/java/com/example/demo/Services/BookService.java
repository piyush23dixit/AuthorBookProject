package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Author;
import com.example.demo.Entities.Book;
import com.example.demo.ExceptionHandling.ResourceNotFoundException;
import com.example.demo.Repository.AuthorRepository;
import com.example.demo.Repository.BookRepository;

@Service
@Component
public class BookService {
   
	@Autowired
	private BookRepository bookRepository;
	
	 @Autowired
	 private AuthorRepository authorRepository;
	
	
	public List<Book> addAllBooks(List<Book> bd){
		return bookRepository.saveAll(bd);
	}
	
	public Book addBook(Book book) {
	    // Ensure the Author is provided in the Book object
	    if (book.getAuthor() == null || book.getAuthor().getAuthorId() == null) {
	        throw new IllegalArgumentException("Author must be specified");
	    }

	    // Fetch the Author from the database using the authorId
	    Optional<Author> optionalAuthor = authorRepository.findById(book.getAuthor().getAuthorId());
	    
	    // Check if the Author exists; if not, throw an exception
	    if (!optionalAuthor.isPresent()) {
	        throw new ResourceNotFoundException("Author does not exist with the specified Author ID");
	    }

	    // Set the Author on the Book object
	    book.setAuthor(optionalAuthor.get());

	    // Save the Book and return the saved entity
	    return bookRepository.save(book);
	}

     public List<Book> getAllBooks(){
    	 return bookRepository.findAll();    
   }
     
     public Page<Book> getPageBooks(Pageable pagaeble){
    	 return bookRepository.findAll(pagaeble);
     }
     
     public Optional<Book> getBookById(String id) {
    	 return bookRepository.findById(id);
     }
     
     public void updateBook(Book bd, String id) {
    	 bd.setBookId(id);
    	 bookRepository.save(bd);
     }
     
     public void deleteBook(String id) {
    	 bookRepository.deleteById(id);
     }
}













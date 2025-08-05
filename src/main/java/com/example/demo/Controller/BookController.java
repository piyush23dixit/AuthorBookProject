package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entities.Book;
import com.example.demo.ExceptionHandling.ResourceNotFoundException;
import com.example.demo.Services.BookService;

import jakarta.validation.Valid;

@Component
@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/Books")
	public ResponseEntity<List<Book>> addAllBooks(@Valid @RequestBody List<Book> bd){
		
		try {
			List<Book> list = this.bookService.addAllBooks(bd);
			return new ResponseEntity(list,HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/Book")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book b){
		
		try {
			Book bd=this.bookService.addBook(b);
			return ResponseEntity.of(Optional.of(bd));
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
   
	@GetMapping("/AllBooks")
	public ResponseEntity<List<Book>> getAllBooks(){
		try {
			List<Book> list=this.bookService.getAllBooks();
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//pagination 
	
	@GetMapping("/PageBooks")
	public ResponseEntity<Page<Book>> getPageBooks(@RequestParam(defaultValue= "0") int page, 
			                                      @RequestParam(defaultValue ="2") int size,
			                                      @RequestParam(defaultValue ="title") String title,
			                                      @RequestParam(defaultValue ="asc") String sortDirection){
		Sort sort = sortDirection.equalsIgnoreCase("asc")?Sort.by(title).ascending():Sort.by(title).descending();
		
		Pageable pageable = PageRequest.of(page, size,sort);
		
		try {
			Page<Book> book=this.bookService.getPageBooks(pageable);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(book);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@GetMapping("/Book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
	    Optional<Book> bookOptional = bookService.getBookById(id);
	    
	    if (bookOptional.isEmpty()) {
	        throw new ResourceNotFoundException("Book not found with id: " + id);
	    }
	    
	    Book book = bookOptional.get();
	    
	    // Log the Author details to verify if they are populated
	    System.out.println("Author: " + book.getAuthor());

	    return ResponseEntity.ok(book);
	}

    @PutMapping("/Book/{id}")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book bd, @PathVariable("id") String id){
    	this.bookService.updateBook(bd,id);
    	return ResponseEntity.ok().body(bd);
    }
	
    @DeleteMapping("/Book/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") String id){
Optional<Book> bookOptional = bookService.getBookById(id);
	    
	    if (bookOptional.isEmpty()) {
	        throw new ResourceNotFoundException("Book not found with id: " + id);
	    }
	    else {
	    	this.bookService.deleteBook(id);
	    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    	}
    }
}






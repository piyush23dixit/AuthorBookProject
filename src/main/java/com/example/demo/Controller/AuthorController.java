package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entities.Author;
import com.example.demo.ExceptionHandling.ResourceNotFoundException;
import com.example.demo.Services.AuthorService;

import jakarta.validation.Valid;

@RestController
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@PostMapping("/Authors")
	public ResponseEntity<List<Author>> addAllAuthors(@Valid @RequestBody List<Author> ad){
		
		List<Author> list=this.authorService.addAllAuthors(ad);
		return new ResponseEntity(list,HttpStatus.CREATED);
	}
	
	@PostMapping("/Author")
	public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author a){
		try {
			Author ad=this.authorService.addAuthor(a);
			return ResponseEntity.of(Optional.of(ad));
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		
	}
    
	@GetMapping("/AllAuthors")
	public ResponseEntity<List<Author>> getAllAuthors(){
		try {
		List<Author> list=this.authorService.getAllAuthors();
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	@GetMapping("/PageAuthors")
//	public ResponseEntity<Page<Author>> getPageAuthors(Pageable pageable){
//		try {
//			 Page<Author> author = authorService.getPageAuthors(pageable);
//	            return ResponseEntity.ok(author);
//			}catch(Exception e) {
//				e.printStackTrace();
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//			}
//	}
	
	
	//Pagination
	
	@GetMapping("/PageAuthors")
	public ResponseEntity<Page<Author>> getPageAuthors(@RequestParam(defaultValue="0") int page,
			                                           @RequestParam(defaultValue="1") int size){
		Pageable pageable=PageRequest.of(page, size);
		try {
			 Page<Author> author = authorService.getPageAuthors(pageable);
	            return ResponseEntity.ok(author);
			}catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
	
	@GetMapping("/Author/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable("id") String id) {
	    Author author = authorService.getAuthorById(id);
	    if (author == null) {
	        throw new ResourceNotFoundException("Author not found with id: " + id);
	    }
	 
	    return ResponseEntity.of(Optional.of(author));
	}
	
	@PutMapping("/Author/{id}")
	public ResponseEntity<Author> updateAuthor(@Valid @PathVariable("id") String id, @RequestBody Author ad){
		 this.authorService.updateAuthor(ad,id);
	    return ResponseEntity.ok().body(ad);
	}
	
	@DeleteMapping("/Author/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable("id") String id){
		Author author= authorService.getAuthorById(id);
		if (author == null) {
	        throw new ResourceNotFoundException("Author not found with id: " + id);
	    }
		else {
			this.authorService.deleteAuthor(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
	}

}









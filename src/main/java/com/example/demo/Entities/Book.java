package com.example.demo.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "BookDetails")  
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString 
public class Book {

    @Id
    @Getter @Setter
    private String bookId;  // Use String for MongoDB's ObjectId
    
    @Getter @Setter
    @NotBlank(message = "Title is Required")
    @Size(min = 4, max = 16, message = "Title must be between 4 to 16 characters")
    private String title;
    
    @Getter @Setter
    @NotBlank(message = "Genre is required")
    @Size(min = 4, max = 10, message = "Genre must be between 4 to 10 Characters")
    private String genre;
    
    @Getter @Setter
    @DBRef  // Use @DBRef for references to other documents in MongoDB
    private Author author;

	

    
}

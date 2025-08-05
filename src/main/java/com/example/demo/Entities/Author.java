package com.example.demo.Entities;

import java.util.List;

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

@Document(collection = "AuthorDetails")  // Use @Document instead of @Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {

    @Id
    @Getter @Setter
    private String authorId;  // Use String for MongoDB's ObjectId
    
    @Getter @Setter
    @NotBlank(message = "Name is Required")
    @Size(min = 4, max = 16, message = "Name must be between 4 to 16 characters")
    private String name;
    
    @Getter @Setter
    @NotBlank(message = "Company Name is required")
    @Size(min = 5, max = 20, message = "Company Name must be between 5 to 20 Characters")
    private String country;

    @DBRef 
    @Getter @Setter
    private List<Book> books;

  
}

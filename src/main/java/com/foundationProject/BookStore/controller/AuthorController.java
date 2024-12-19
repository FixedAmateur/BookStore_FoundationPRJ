package com.foundationProject.BookStore.controller;


import com.foundationProject.BookStore.model.dto.AuthorDto;
import com.foundationProject.BookStore.model.response.ApiResponse;
import com.foundationProject.BookStore.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@Tag(name = "Author", description = "Author: Firstname Lastname")
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Get All Authors", description = "Get All Authors API")
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllAuthors() {
        ApiResponse apiResponse = ApiResponse.success(authorService.getAllAuthors());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Create Author", description = "Create Author API")
    @PostMapping()
    public ResponseEntity<ApiResponse> createAuthor(@RequestBody @Valid AuthorDto authorRequest) {
        ApiResponse apiResponse = ApiResponse.success(authorService.createAuthor(authorRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Author By Id", description = "Get Author By Id API")
    @GetMapping("/{authorId}")
    public ResponseEntity<ApiResponse> getAuthorById(@PathVariable("authorId") Long authorId) {
        ApiResponse apiResponse = ApiResponse.success(authorService.getAuthor(authorId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update Author", description = "Update Author API")
    @PutMapping("/{authorId}")
    public ResponseEntity<ApiResponse> updateAuthor(@PathVariable("authorId") Long authorId, @Valid @RequestBody AuthorDto authorRequest) {
        ApiResponse apiResponse = ApiResponse.success(authorService.updateAuthor(authorId, authorRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete Author", description = "Delete Author API")
    @DeleteMapping("/{authorId}")
    public ResponseEntity<ApiResponse> deleteAuthor(@PathVariable("authorId") Long authorId) {
        ApiResponse apiResponse = ApiResponse.success(authorService.deleteAuthor(authorId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}

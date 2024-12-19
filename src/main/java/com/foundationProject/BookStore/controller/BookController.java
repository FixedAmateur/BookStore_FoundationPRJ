package com.foundationProject.BookStore.controller;


import com.foundationProject.BookStore.model.dto.BookDto;
import com.foundationProject.BookStore.model.response.ApiResponse;
import com.foundationProject.BookStore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Book API")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Create Book", description = "Create Book API")
    @PostMapping()
    public ResponseEntity<ApiResponse> createBook(@RequestBody @Valid BookDto bookRequest) {
        ApiResponse apiResponse = ApiResponse.success(bookService.createBook(bookRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Book", description = "Get All Book API")
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllBook(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "bookTitle", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy).ascending());
        ApiResponse apiResponse = ApiResponse.success(bookService.getAllBook(pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Book By Id", description = "Get Book By Id API")
    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse> getBookById(@PathVariable("bookId") Long bookId
    ) {
        ApiResponse apiResponse = ApiResponse.success(bookService.getBook(bookId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Book By Title", description = "Get Book By Title API")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> getBookByTitle(
            @RequestParam("bookTitle") String bookTitle,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "bookTitle", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy).ascending());
        ApiResponse apiResponse = ApiResponse.success(bookService.getBookByTitle(bookTitle, pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Book By Author", description = "Get Book By Author API")
    @GetMapping("/author")
    public ResponseEntity<ApiResponse> getBookByAuthorId(
            @RequestParam("authorName") String authorName,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "bookTitle", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy).ascending());
        ApiResponse apiResponse = ApiResponse.success(bookService.getAllBookByAuthorId(authorName, pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Book By Category", description = "Get Book By Category API")
    @GetMapping("/category")
    public ResponseEntity<ApiResponse> getBookByCategory(
            @RequestParam("categoryName") String categoryName,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "bookTitle", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy).ascending());
        ApiResponse apiResponse = ApiResponse.success(bookService.getAllBookByCategory(categoryName, pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update Book", description = "Update Book API")
    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable("bookId") Long bookId, @RequestBody @Valid  BookDto bookRequest) {
        ApiResponse apiResponse = ApiResponse.success(bookService.updateBook(bookId, bookRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete Book", description = "Delete Book API")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable("bookId") Long bookId) {
        ApiResponse apiResponse = ApiResponse.success(bookService.deleteBook(bookId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

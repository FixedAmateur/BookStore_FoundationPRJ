package com.foundationProject.BookStore.service;

import com.foundationProject.BookStore.model.dto.BookDto;
import com.foundationProject.BookStore.model.response.BookResponse;
import com.foundationProject.BookStore.model.response.PageCustom;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponse createBook(BookDto bookRequest);

    BookResponse getBook(Long bookId);

    BookResponse updateBook(Long bookId, BookDto bookRequest);


    String deleteBook(Long bookId);

    PageCustom<BookResponse> getBookByTitle(String bookTitle, Pageable pageable);

    PageCustom<BookResponse> getAllBook(Pageable pageable);

    PageCustom<BookResponse> getAllBookByAuthorId(String authorName, Pageable pageable);

    PageCustom<BookResponse> getAllBookByCategory(String categoryName, Pageable pageable);
}
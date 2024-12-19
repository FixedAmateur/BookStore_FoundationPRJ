package com.foundationProject.BookStore.service;

import com.foundationProject.BookStore.model.dto.AuthorDto;
import com.foundationProject.BookStore.model.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse createAuthor(AuthorDto authorDto);

    AuthorResponse getAuthor(Long authorId);

    String deleteAuthor(Long authorId);

    AuthorResponse updateAuthor(Long authorId, AuthorDto authorDto);

    List<AuthorResponse> getAllAuthors();
}

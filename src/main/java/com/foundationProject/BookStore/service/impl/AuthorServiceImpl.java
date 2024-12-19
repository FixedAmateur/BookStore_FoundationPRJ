package com.foundationProject.BookStore.service.impl;

import com.foundationProject.BookStore.model.dto.AuthorDto;
import com.foundationProject.BookStore.model.response.AuthorResponse;
import com.foundationProject.BookStore.model.entity.Author;
import com.foundationProject.BookStore.repository.AuthorRepository;
import com.foundationProject.BookStore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public AuthorResponse createAuthor(AuthorDto authorRequest) {
        if (authorRepository.existsByAuthorName(authorRequest.getAuthorName())) {
            throw new RuntimeException("Author with name " + authorRequest.getAuthorName() + " already exists");
        }
        Author author = modelMapper.map(authorRequest, Author.class);
        return modelMapper.map(authorRepository.save(author), AuthorResponse.class);
    }

    @Override
    public AuthorResponse getAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        return modelMapper.map(author, AuthorResponse.class);
    }

    @Override
    public String deleteAuthor(Long authorId) {
        Author author =authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.delete(author);
        return "Author with id: " +authorId+ " was deleted successfully";
    }

    @Override
    public AuthorResponse updateAuthor(Long authorId, AuthorDto authorRequest) {
        if (authorRepository.existsByAuthorName(authorRequest.getAuthorName())) {
            throw new RuntimeException("Author with name " + authorRequest.getAuthorName() + " already exists");
        }
        Author author =authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
         modelMapper.map(authorRequest, author);
        return modelMapper.map(authorRepository.save(author), AuthorResponse.class);
    }

    @Override
    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> modelMapper.map(author, AuthorResponse.class)).toList();
    }



}

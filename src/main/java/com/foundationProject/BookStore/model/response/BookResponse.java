package com.foundationProject.BookStore.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long bookId;
    private String bookTitle;
    private String bookDescription;
    private Long unitPrice;
    private Long boughtAmount;
    private Set<CategoryResponse> categories;
    private AuthorResponse author;
}

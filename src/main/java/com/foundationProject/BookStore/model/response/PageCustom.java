package com.foundationProject.BookStore.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageCustom<T> {
    private int pageNo;
    private int pageSize;
    private int totalPages;
    List<T> pageContent;
}

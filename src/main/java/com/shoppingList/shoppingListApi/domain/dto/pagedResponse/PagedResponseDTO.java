package com.shoppingList.shoppingListApi.domain.dto.pagedResponse;

import java.util.List;

public record PagedResponseDTO<T>(
        String previousPage,
        String nextPage,
        int currentPage,
        int pageSize,
        long totalElements,
        long totalPages,
        long count,
        List<T> data
) {
}

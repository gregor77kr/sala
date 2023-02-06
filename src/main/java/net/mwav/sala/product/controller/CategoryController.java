package net.mwav.sala.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.common.dto.StandardResponseBody;
import net.mwav.sala.product.dto.CategoryResponse;
import net.mwav.sala.product.entity.Category;
import net.mwav.sala.product.service.CategoryService;

@RestController
@RequestMapping(value = "/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCategories() {
        List<Category> categories = categoryService.getCategories();
        List<CategoryResponse> categoryResponses = categories.stream().map(CategoryResponse::from)
                .collect(Collectors.toList());
        log.debug(categoryResponses.toString());
        StandardResponseBody<List<CategoryResponse>> standardResponseBody = StandardResponseBody
                .success(categoryResponses);

        return ResponseEntity.status(HttpStatus.CREATED).body(standardResponseBody);
    }
}

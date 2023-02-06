package net.mwav.sala.product.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Value;
import net.mwav.sala.product.entity.Category;

@Value
@Builder
public class CategoryResponse implements Serializable {

    private long id;

    private String name;

    private String description;

    private boolean isActive = true;

    private List<ProductResponse> products;

    public static CategoryResponse from(Category category) {
        List<ProductResponse> productResponses = category.getProducts().stream().map(p -> {
            return ProductResponse.from(p.getProduct());
        }).collect(Collectors.toList());

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .products(productResponses)
                .build();
    }
}

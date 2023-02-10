package net.mwav.sala.product.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;
import net.mwav.sala.product.entity.Product;

@Value
@Builder
public class ProductResponse implements Serializable {

	private static final long serialVersionUID = -4614209846156206562L;

	private long id;

	private String name;

	private String description;

	private boolean isActive;

	public static ProductResponse from(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.isActive(product.isActive())
				.build();
	}
}

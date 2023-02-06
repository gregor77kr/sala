package net.mwav.sala.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.common.dto.StandardResponseBody;
import net.mwav.sala.customer.dto.ProfileResponse;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProduct(@PathVariable("productId") long productId) {
        StandardResponseBody<ProfileResponse> standardResponseBody = StandardResponseBody
                .success();

        return ResponseEntity.status(HttpStatus.CREATED).body(standardResponseBody);
    }

}

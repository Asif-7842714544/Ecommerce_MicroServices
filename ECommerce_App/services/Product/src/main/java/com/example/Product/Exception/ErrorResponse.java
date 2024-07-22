package com.example.Product.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}

package com.asif.Ecommerce.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}

package com.asif.Ecommerce.DTO;

import com.asif.Ecommerce.Entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CustomerRequest(

        String id,

        @NotNull(message = "customer firstName is required")
        String firstName,

        @NotNull(message = "customer lastName is required")
        String lastName,

        @NotNull(message = "customer email is required")
        @Email(message = "customer email is not a valid email address")
        String email,

        List<Address> address
) {
}

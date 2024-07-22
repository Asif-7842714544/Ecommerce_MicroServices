package com.asif.Ecommerce.DTO;

import com.asif.Ecommerce.Entity.Address;

import java.util.List;

public record CustomerResponse(
        String id,

        String firstName,

        String lastName,

        String email,

        List<Address> address

) {
}

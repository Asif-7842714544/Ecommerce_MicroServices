package com.asif.Ecommerce.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
@Entity
public class Address {

    @Id
    private String addressId;
    private String street;
    private String houseNumber;
    private String zipCode;

}

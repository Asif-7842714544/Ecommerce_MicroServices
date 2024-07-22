package com.asif.Ecommerce.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany
    private List<Address> address;
}

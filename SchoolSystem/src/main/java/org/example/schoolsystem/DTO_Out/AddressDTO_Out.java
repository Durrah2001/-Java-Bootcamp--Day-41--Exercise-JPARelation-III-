package org.example.schoolsystem.DTO_Out;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO_Out {


    private String street;

    private Integer buildingNumber;
}

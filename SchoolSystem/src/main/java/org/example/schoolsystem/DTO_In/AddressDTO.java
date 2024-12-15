package org.example.schoolsystem.DTO_In;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private Integer teacher_id;

    @NotEmpty(message = "Street can not be empty!")
    private String street;

    private Integer buildingNumber;


}

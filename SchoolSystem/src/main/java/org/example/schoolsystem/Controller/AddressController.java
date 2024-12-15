package org.example.schoolsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolsystem.ApiResponse.ApiResponse;
import org.example.schoolsystem.DTO_In.AddressDTO;
import org.example.schoolsystem.DTO_Out.AddressDTO_Out;
import org.example.schoolsystem.Model.Address;
import org.example.schoolsystem.Service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school-system/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @GetMapping("/get")
    public ResponseEntity getAllAddresses(){
        List<AddressDTO_Out> addresses = addressService.getAllAddress();
        return ResponseEntity.status(200).body(addresses);
    }



    @PostMapping("/add")
    public ResponseEntity addAddress(@RequestBody @Valid AddressDTO addressDTO){

        addressService.addAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Address added successfully!"));
    }

    @PutMapping("/update")
    public ResponseEntity updateAddress(@RequestBody @Valid AddressDTO addressDTO){

        addressService.updateAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Address updated successfully!"));
    }


}

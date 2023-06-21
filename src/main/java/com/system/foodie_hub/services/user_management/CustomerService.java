package com.system.foodie_hub.services.user_management;

import com.system.foodie_hub.dto.CustomerDto;
import com.system.foodie_hub.entity.user_management.Customer;

import java.util.List;

public interface CustomerService {
    String saveData(CustomerDto customerDto);

    List<Customer> getData();
}

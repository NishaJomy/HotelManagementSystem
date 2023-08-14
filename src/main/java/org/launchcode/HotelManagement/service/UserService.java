package org.launchcode.HotelManagement.service;

import org.launchcode.HotelManagement.dto.UserDto;
import org.launchcode.HotelManagement.models.User;

public interface UserService {
    User findByUsername(String username);
    User save (UserDto userDto);

}

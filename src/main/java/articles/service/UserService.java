package articles.service;

import java.util.List;

import articles.dto.UserDto;
import articles.entity.User;



public interface UserService {
	
	void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

}

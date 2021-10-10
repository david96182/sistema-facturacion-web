package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.UserDto;

public interface UserService {
	List<UserDto> getUsers();
	UserDto getUserById(String userId);
	UserDto getUserByUsername(String username);
	void createUser(UserDto user);
	void updateUser(UserDto user);
	void deleteUser(String id);
}

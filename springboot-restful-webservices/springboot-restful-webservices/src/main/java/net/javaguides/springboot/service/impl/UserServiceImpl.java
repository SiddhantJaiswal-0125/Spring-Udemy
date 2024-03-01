package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        //Convert User DTO to USer JPA
        //Extracting a User JPA from UserDto

        User user  = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        User savedUser=  userRepository.save(user);

        // Convert User JPA entity to UserDTO
        UserDto saverUserDto = new UserDto(savedUser.getId(),
                savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
        return  saverUserDto;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser =  userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User currentUser = userRepository.findById(user.getId()).get();

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        return  userRepository.save(currentUser);


    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

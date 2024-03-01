package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        //Convert User DTO to USer JPA
        //Extracting a User JPA from UserDto

        User user  = UserMapper.mapToUser(userDto);
        User savedUser=  userRepository.save(user);

        // Convert User JPA entity to UserDTO
        UserDto saverUserDto = UserMapper.mapToUserDto(savedUser);
        return  saverUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser =  userRepository.findById(userId);


        UserDto userDto = UserMapper.mapToUserDto(optionalUser.get());
        return userDto;

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers =  userRepository.findAll();

        List<UserDto> allUserDto = new ArrayList<>();
        return  allUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
     }

    @Override
    public UserDto updateUser(UserDto user) {
        User currentUser = userRepository.findById(user.getId()).get();

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        User savedUser =  userRepository.save(currentUser);
        return  UserMapper.mapToUserDto(savedUser);

    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

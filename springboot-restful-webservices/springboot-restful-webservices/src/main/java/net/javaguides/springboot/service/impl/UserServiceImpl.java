package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        //Convert User DTO to USer JPA
        //Extracting a User JPA from UserDto

//        User user  = UserMapper.mapToUser(userDto);

        User user  = modelMapper.map(userDto, User.class);

        User savedUser=  userRepository.save(user);

        // Convert User JPA entity to UserDTO
//        UserDto saverUserDto = UserMapper.mapToUserDto(savedUser);

        UserDto saverUserDto = modelMapper.map(savedUser, UserDto.class);
        return  saverUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
       User user =  userRepository.findById(userId).orElseThrow(
               () -> new ResourceNotFoundException("User", "id", userId)

       );
//        UserDto userDto = UserMapper.mapToUserDto(optionalUser.get());


        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers =  userRepository.findAll();

        List<UserDto> allUserDto = new ArrayList<>();
//        return  allUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

    return  allUsers.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

    }


    @Override
    public UserDto updateUser(UserDto user) {
        User currentUser = userRepository.findById(user.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("User", "Id", user.getId())
        );

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        User savedUser =  userRepository.save(currentUser);

//        return UserMapper.mapToUserDto(savedUser)
        return  modelMapper.map(savedUser, UserDto.class);

    }

    @Override
    public void deleteUser(Long userId) {

        User existingUser = userRepository.findById(userId).orElseThrow(
                () ->  new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }
}

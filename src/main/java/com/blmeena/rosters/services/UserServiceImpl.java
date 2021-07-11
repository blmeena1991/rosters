package com.blmeena.rosters.services;

import com.blmeena.rosters.exceptions.ValidationException;
import com.blmeena.rosters.models.Profile;
import com.blmeena.rosters.models.User;
import com.blmeena.rosters.models.UserLike;
import com.blmeena.rosters.models.dto.ProfileDto;
import com.blmeena.rosters.models.dto.UserDto;
import com.blmeena.rosters.repositories.ProfileRepository;
import com.blmeena.rosters.repositories.UserLikeRepository;
import com.blmeena.rosters.repositories.UserRepository;
import com.blmeena.rosters.security.UserPrincipal;
import com.blmeena.rosters.util.BeanMapper;
import com.blmeena.rosters.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProfileRepository profileRepository;

    @Autowired
    public UserLikeRepository userLikeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserDto userDto) {
        User user = findUser(userDto);
        if(user != null){
            throw new ValidationException("Username or email is already taken!");
        }
        user = new User();
        copyNonNullProperties(userDto,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        if(user.getId() != null){
            Profile profile = new Profile();
            copyNonNullProperties(userDto.getProfile(), profile);
            profile.setUser(user);
            profileRepository.save(profile);
            user.setProfile(profile);
        }
        return convertUserToDto(user);
    }



    @Override
    public UserDto update(Long id, UserDto userDto) {
        return null;
    }

    @Override
    public Page<UserDto> index(UserDto userDto, Pageable pageable) {
        return userRepository.findAll(pageable).map(this::convertUserToDto);
    }

    @Override
    public UserDto userVote(Map<String, Object> body, UserPrincipal currentUser) {
        Optional<User> userFrom = userRepository.findById(currentUser.getId());
        Optional<User> userTo = userRepository.findById(Long.valueOf(body.get("following_id").toString()));
        if( !userTo.isPresent() || !userFrom.isPresent()){
            throw new ValidationException("Invalid request");
        }
        UserLike userLike = new UserLike();
        userLike.setFrom(userFrom.get());
        userLike.setTo(userTo.get());
        userLikeRepository.save(userLike);
        return convertUserToDto(userTo.get());
    }

    private UserDto convertUserToDto(User user) {
        BeanMapper mapper = new BeanMapper();
        UserDto userDto =  mapper.map(user, UserDto.class);
        userDto.setVoteCount(userLikeRepository.getUserVoteCount(user.getId()));
        userDto.setProfile(convertProfileToDto(user.getProfile()));
        return userDto;
    }

    private ProfileDto convertProfileToDto(Profile profile) {
        BeanMapper mapper = new BeanMapper();
        return mapper.map(profile, ProfileDto.class);
    }
    private static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, CommonUtils.getNullPropertyNames(src));
    }
    private User findUser(UserDto userDto){
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());
        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        userOptional = userRepository.findByUserName(userDto.getUserName());

        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }
}

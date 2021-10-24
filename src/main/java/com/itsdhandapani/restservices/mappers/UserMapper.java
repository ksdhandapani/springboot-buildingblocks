package com.itsdhandapani.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.itsdhandapani.restservices.dtos.UserMsDTO;
import com.itsdhandapani.restservices.entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	// for User object - User to UserMsDTO
	@Mappings({ @Mapping(source = "email", target = "emailAddress"), @Mapping(source = "role", target = "roleName") })
	UserMsDTO userToUserMsDTO(User user);

	// for List<User> object - List<User> to List<UserMsDTO>
	List<UserMsDTO> usesToUserMsDTOs(List<User> users);

}

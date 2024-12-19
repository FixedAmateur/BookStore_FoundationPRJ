package com.foundationProject.BookStore.service.impl;

import com.foundationProject.BookStore.exception.ResourceNotFoundException;
import com.foundationProject.BookStore.model.dto.RoleDto;
import com.foundationProject.BookStore.model.entity.Role;
import com.foundationProject.BookStore.model.response.RoleResponse;
import com.foundationProject.BookStore.repository.RoleRepository;
import com.foundationProject.BookStore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Override
    public RoleResponse createRole (RoleDto roleRequest){
        Role role = modelMapper.map(roleRequest, Role.class);
        return modelMapper.map(roleRepository.save(role),RoleResponse.class);
    }

    @Override
    public RoleResponse getRoleByRoleName(String roleName){
        Role role = roleRepository.findByRoleName(roleName).orElseThrow(()->new ResourceNotFoundException("role","role's name",roleName));
        return modelMapper.map(role,RoleResponse.class);
    }

    @Override
    public RoleResponse getRoleById(Long roleId){
        Role role = roleRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("role","role's name",roleId));
        return modelMapper.map(role,RoleResponse.class);
    }

    @Override
    public List<RoleResponse> getAllRole(){
        return roleRepository.findAll().stream()
                .map(result->modelMapper.map(result,RoleResponse.class)).toList();
    }

    @Override
    public String deleteRole(Long roleId){
        Role role = roleRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("role","role's name",roleId));
        roleRepository.delete(role);
        return "Role "+ roleId+" was delete successfully";
    }


}

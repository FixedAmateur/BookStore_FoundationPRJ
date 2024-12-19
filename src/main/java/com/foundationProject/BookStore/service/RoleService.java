package com.foundationProject.BookStore.service;

import com.foundationProject.BookStore.model.dto.RoleDto;
import com.foundationProject.BookStore.model.response.RoleResponse;

import java.util.List;

public interface RoleService  {
    RoleResponse createRole (RoleDto roleRequest);

    RoleResponse getRoleByRoleName(String roleName);

    RoleResponse getRoleById(Long roleId);

    List<RoleResponse> getAllRole();

    String deleteRole(Long roleId);
}

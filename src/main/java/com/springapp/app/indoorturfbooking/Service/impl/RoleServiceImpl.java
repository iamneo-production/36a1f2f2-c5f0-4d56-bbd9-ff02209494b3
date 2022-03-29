package com.springapp.app.indoorturfbooking.Service.impl;

import com.springapp.app.indoorturfbooking.Entity.Role;
import com.springapp.app.indoorturfbooking.Repository.RoleRepository;
import com.springapp.app.indoorturfbooking.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        Role role=roleRepository.findRoleByName(name);
        return role;
    }
}

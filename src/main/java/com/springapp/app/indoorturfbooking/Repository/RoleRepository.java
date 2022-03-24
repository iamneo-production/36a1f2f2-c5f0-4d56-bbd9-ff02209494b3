package com.springapp.app.indoorturfbooking.Repository;


import com.springapp.app.indoorturfbooking.Entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findRoleByName(String name);
}

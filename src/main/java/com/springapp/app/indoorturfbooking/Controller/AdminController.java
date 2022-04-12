package com.springapp.app.indoorturfbooking.Controller;

import java.util.List;


import com.springapp.app.indoorturfbooking.Entity.Ground;
import com.springapp.app.indoorturfbooking.Entity.User;
import com.springapp.app.indoorturfbooking.Entity.UserDto;
import com.springapp.app.indoorturfbooking.Exception.GroundException;
import com.springapp.app.indoorturfbooking.Exception.ResourceNotFoundException;
import com.springapp.app.indoorturfbooking.Exception.UserDataExistsException;

import com.springapp.app.indoorturfbooking.Response.ResponseModel;
import com.springapp.app.indoorturfbooking.Service.UserService;
import com.springapp.app.indoorturfbooking.Service.impl.UserServiceImpl;
import com.springapp.app.indoorturfbooking.Service.impl.userServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserServiceImpl userService;


    @Autowired
    userServ userServ;

/*
    @Autowired
    private GroundService serv;

    @GetMapping("/dashboard")
    public ResponseEntity<List<Ground>> availableGround(){
        List<Ground> available=serv.availableGround();
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

    @PostMapping("/addGround")
    public Ground addGround(@RequestBody Ground gm){
        return serv.saveGround(gm);
    }

    @PutMapping("/editGround/{id}")
    public ResponseEntity<Ground> editGround(@RequestBody Ground gm){
        Ground edit=serv.editGround(gm);
        return new ResponseEntity<>(edit,HttpStatus.OK);
    }

    @DeleteMapping("/deleteGround/{id}")
    public ResponseEntity<?> deleteGround(@PathVariable(value="id") Long id){
        serv.deleteGround(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
*/

    @RequestMapping(value = "/registeradmin",method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user) throws UserDataExistsException {
        return userService.savea(user);
    }

    // Display all Customers.
   /* @GetMapping("/list/users")
    public ResponseEntity<List<Person>> getAllCustomers() throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<List<Person>>(userServ.getAllUsersInfo("USER") ,HttpStatus.OK);
    }


    // Display all Admins.
    @GetMapping("/list/admins")
    public ResponseEntity<List<Person>> getAllAdmins() throws ResourceNotFoundException, Exception
    {
        return new ResponseEntity<List<Person>>(userServ.getAllUsersInfo("ADMIN") ,HttpStatus.OK);
    }
*/

    // Add New Ground
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addGround")
    public ResponseEntity<ResponseModel> addNewGround(@RequestBody Ground ground) throws GroundException, Exception
    {
        return new ResponseEntity<ResponseModel>(userServ.addGround(ground), HttpStatus.ACCEPTED);
    }


    // Edit Ground
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editGround/{id}")
    public ResponseEntity<Ground> editGround(@PathVariable("id") long groundId, @RequestBody Ground ground) throws GroundException, Exception
    {
        return new ResponseEntity<Ground>(userServ.editGround(groundId, ground), HttpStatus.OK);
    }

    @RequestMapping(value = "/editUser/{id}",method = RequestMethod.PUT)
    public User editUser(@RequestBody UserDto user,@PathVariable("id") long userId) throws UserDataExistsException {

        return userService.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteGround/{id}")
    public ResponseEntity<ResponseModel> deleteGround(@PathVariable("id") long groundId) throws GroundException, Exception
    {
        return new ResponseEntity<ResponseModel>(userServ.deleteGround(groundId), HttpStatus.OK);
    }

    //Display all grounds.
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard")
    public ResponseEntity<List<Ground>> showAllGrounds()
    {
        return new ResponseEntity<List<Ground>>(userServ.showAllGrounds(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> showAllUsers()
    {
        return new ResponseEntity<List<User>>(userService.finaAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ResponseModel> deleteuser(@PathVariable("id") long userId) throws UsernameNotFoundException,Exception
    {
        return new ResponseEntity<ResponseModel>(userServ.deleteUser(userId), HttpStatus.OK);
    }


}

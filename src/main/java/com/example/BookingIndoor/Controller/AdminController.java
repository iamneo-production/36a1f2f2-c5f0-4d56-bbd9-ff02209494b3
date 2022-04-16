package com.example.BookingIndoor.Controller;

import com.example.BookingIndoor.Exception.BookingException;
import com.example.BookingIndoor.Exception.GroundException;
import com.example.BookingIndoor.Model.Ground;
import com.example.BookingIndoor.Model.User;
import com.example.BookingIndoor.Service.GroundService;
import com.example.BookingIndoor.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class AdminController {

    @Autowired
    private UserService service;

    @Autowired
    private GroundService serv;


    public String Default = "ADMIN";

    @PostMapping("/signup")
    public String saveAdmin(@RequestBody User um) {
        //um.setRoles(Default);
        /*String pwd = um.getPassword();
        String encryptPwd = pwdEncoder.encode(pwd);
        um.setPassword(encryptPwd);*/
        um.setBookings(null);
        um.setRole(Default);
        service.saveAdmin(um);
        return "Registration Successful";
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<Ground>> availableGround() throws BookingException, GroundException {
        List<Ground> available = serv.availableAdminGround();
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

    @GetMapping("/viewground/{id}")
    public ResponseEntity<Ground> viewGroundById(@PathVariable(value="id") Long id) throws Exception {
        Ground ground=serv.finByGroundId(id);
        return new ResponseEntity<Ground>(ground,HttpStatus.CREATED);
    }

    @PostMapping("/addGround")
    public ResponseEntity<Ground> addGround(@RequestBody Ground ground) throws Exception {
            Ground ground1 = serv.saveGround(ground);
            return new ResponseEntity<Ground>(serv.saveGround(ground), HttpStatus.CREATED);
    }

    @PutMapping("/editGround/{id}")
    public ResponseEntity<Ground> editGround(@PathVariable(value="id") Long id,@RequestBody Ground ground) throws Exception {
        Ground edit = serv.editGround(ground,id);
        return new ResponseEntity<>(edit, HttpStatus.OK);
    }

    @DeleteMapping("/deleteGround/{id}")
    public ResponseEntity<?> deleteGround(@PathVariable(value = "id") Long id) throws Exception {
        serv.deleteGround(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/viewuser")
    public ResponseEntity<List<User>> userAvailable(){
        List<User> users=service.findUser();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("/editUser/{id}")
    public ResponseEntity<User> editUser(@RequestBody User user,@PathVariable(value="id") Long id){
        user.setId(id);
        User u=service.saveUser(user);
        return new ResponseEntity<User>(u,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value="id") Long id){
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
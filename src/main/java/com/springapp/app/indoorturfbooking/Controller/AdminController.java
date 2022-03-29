package com.springapp.app.indoorturfbooking.Controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
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


    @GetMapping("/dashboard")
    public ResponseEntity<String> availableGround(){
       String hello="hello hi welcome";
        return new ResponseEntity<>(hello, HttpStatus.OK);
    }
}

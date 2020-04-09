package com.uxpsystems.assignment.controller;

import com.uxpsystems.assignment.exception.RecordNotFoundException;
import com.uxpsystems.assignment.model.EmployeeEntity;
import com.uxpsystems.assignment.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * Created by kchaurasia on 4/9/2020.
 */


@RestController
@RequestMapping("/employees")
public class EmployeeController


{

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService service;
 
    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        logger.info("In getAllEmployees of EmployeeController");
        List<EmployeeEntity> list = service.getAllEmployees();
 
        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        logger.info("In getEmployeeById of EmployeeController");
        EmployeeEntity entity = service.getEmployeeById(id);
 
        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee)
                                                    throws RecordNotFoundException {
        logger.info("In getEmployeeById of createEmployee");
        EmployeeEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> update(@RequestBody EmployeeEntity employee, @PathVariable("id") Long id)  throws RecordNotFoundException{
        logger.info("In update of createEmployee");
            EmployeeEntity existUser = service.getEmployeeById(id);


            if(existUser!=null) {
                try {
                    service.update(existUser, employee);
                    return new ResponseEntity<EmployeeEntity>(existUser, new HttpHeaders(), HttpStatus.OK);


                } catch (NoSuchElementException e) {
                    return new ResponseEntity<EmployeeEntity>(HttpStatus.NOT_FOUND);
                }
            }
        else
            {
                return new ResponseEntity<EmployeeEntity>(HttpStatus.NOT_FOUND);
            }
    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {

        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}
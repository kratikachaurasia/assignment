package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.exception.RecordNotFoundException;
import com.uxpsystems.assignment.model.EmployeeEntity;
import com.uxpsystems.assignment.dao.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by kchaurasia on 4/9/2020.
 */

 
@Service
@CacheConfig(cacheNames="employees")
public class EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeService.class);
     
    @Autowired
    EmployeeRepository repository;

    @Cacheable()
    public List<EmployeeEntity> getAllEmployees()
    {
        logger.info("In getAllEmployees of EmployeeService ");
        List<EmployeeEntity> employeeList =  new ArrayList<EmployeeEntity>();
        try {
            employeeList = repository.findAll();

            if (employeeList.size() > 0) {
                return employeeList;

            }

        }
        catch (Exception e) {
                    logger.error("Error in getAllEmployees of EmployeeService " + e);
                }



    return employeeList;

    }
     
    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException
    {
        try
        {

        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
        }
        catch(Exception e)
        {
            logger.error("Error in getEmployeeById of EmployeeService "+e);
            throw new RecordNotFoundException("No employee record exist for given id");

        }

    }
     
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException
    {
try
{
    entity = repository.save(entity);
}
catch(Exception e)
{
    logger.error("Error in createOrUpdateEmployee of EmployeeService "+e);
}
 return entity;

    }
    public EmployeeEntity update(EmployeeEntity existEmployee, EmployeeEntity newEntity) throws RecordNotFoundException
    {

        newEntity.setId(existEmployee.getId());


        newEntity = repository.save(newEntity);
    return newEntity;
    }

     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
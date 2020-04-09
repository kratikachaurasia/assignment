package com.uxpsystems.assignment;

/**
 * Created by kchaurasia on 4/9/2020.
 */

import com.uxpsystems.assignment.controller.EmployeeController;
import com.uxpsystems.assignment.exception.RecordNotFoundException;
import com.uxpsystems.assignment.model.EmployeeEntity;
import com.uxpsystems.assignment.dao.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)

public class MainApplicationTest
{
@InjectMocks
EmployeeController employeeController;

@Mock
EmployeeRepository employeeRepository;



    @Test
    public void testCreateEmployee() throws RecordNotFoundException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));



        EmployeeEntity employee = new EmployeeEntity(1L, "user1", "user1234", "active");
        ResponseEntity<EmployeeEntity> responseEntity = employeeController.createEmployee(employee);

        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employee);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }
}

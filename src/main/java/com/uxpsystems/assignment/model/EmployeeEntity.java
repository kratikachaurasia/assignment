package com.uxpsystems.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Created by kchaurasia on 4/9/2020.
 */


@Entity
@Table(name="TBL_USER")
public class EmployeeEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;
    
    @Column(name="status")
    private String status;

    public EmployeeEntity()
    {

    }
    public EmployeeEntity(long l, String user1, String user1234, String active) {

    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    @Override
    public String toString() {
        return "EmployeeEntity [id=" + id + ", userName=" + userName +
                ", password=" + password + ", status=" + status   + "]";
    }
}
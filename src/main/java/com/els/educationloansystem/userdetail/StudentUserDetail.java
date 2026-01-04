package com.els.educationloansystem.userdetail;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.els.educationloansystem.entity.Student;

public class StudentUserDetail implements UserDetails{
	
	//db entity/user
	private Student student;

	public StudentUserDetail(Student student) {
		super();
		this.student = student;
	}

	@Override
    public String getUsername() {
        return student.getEmail();
    }

    @Override
    public String getPassword() {
        return student.getPassword(); // BCrypt password
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_STUDENT");
    }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

}


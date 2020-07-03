package com.cuongnm.mercari.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jwt_blacklist")
public class JwtBlacklist {
	 	@Id
	 	@GeneratedValue
	 	@Column(name = "id")
	    private Long id;
	 	
	 	@Column(name = "token")
	    private String token;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

	    @Override
	    public String toString() {
	        return "JwtBlacklist{" +
	                "id='" + id + '\'' +
	                ", token='" + token + '\'' +
	                '}';
	    }
}

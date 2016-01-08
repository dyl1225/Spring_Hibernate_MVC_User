package com.dyl.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//用户与角色为多对多
@Entity
public class User1
{
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private Set<Role> roles=new HashSet<Role>();
	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="user_role",
			joinColumns={@JoinColumn(name="user_id")},
			inverseJoinColumns={@JoinColumn(name="role_id")}
			)
	
	public Set<Role> getRoles()
	{
		return roles;
	}
	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}
}

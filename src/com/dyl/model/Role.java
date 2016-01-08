package com.dyl.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
@Entity
public class Role
{
	private int id;
	private  String name;
	private String description;
	//这个角色有哪些权限
	private Set<Privilege> privileges=new HashSet<Privilege>();
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
	@JoinColumn(nullable=false,unique=true)
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	@ManyToMany
	@JoinTable(
			name="role_privilege",
			joinColumns={@JoinColumn(name="role_id")},
			inverseJoinColumns={@JoinColumn(name="privilege_id")}
			)
	public Set<Privilege> getPrivileges()
	{
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges)
	{
		this.privileges = privileges;
	}
}

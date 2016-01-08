package com.dyl.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Resource
{
	private int id;
	private String uri;
	private String description;
	//被那个权限控制
	private Privilege privilege;
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
	@JoinColumn(nullable=true,unique=true)
	public String getUri()
	{
		return uri;
	}
	public void setUri(String uri)
	{
		this.uri = uri;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="privilege_id")
	public Privilege getPrivilege()
	{
		return privilege;
	}
	public void setPrivilege(Privilege privilege)
	{
		this.privilege = privilege;
	}
}

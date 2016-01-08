package com.dyl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
@Entity
//权限类，与资源是一对多的关系Resource,与角色是多对多的关系
public class Privilege
{

	private int id;
	private String name;
	private String description;
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
	
	@Override
	public boolean equals(Object obj)
	{

		if(obj instanceof Privilege)
		{
			Privilege p=(Privilege) obj;
			if(this.id==p.getId()&&this.name.equals(p.getName())&&this.description.equals(p.getDescription()))
			{
				return true;
			}
			
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return this.name.hashCode();
	}
}

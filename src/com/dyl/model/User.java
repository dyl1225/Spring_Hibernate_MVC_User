package com.dyl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="t_user")
public class User
{
	/*
	 * 今天调试个程序，
       有个名为isAdmin的boolean类型的变量在jsp页面获取到的值为空，
                  这根本就是没获取到或者变量不存在的状况啊，但是在Action中明明是赋值成false了。
              上网查了一下有的同学也遇到了这样的问题，这个很好解决，看看代码中isAdmin的get方法，
                   名字是isAdmin，改成getIsAdmin就好了。原因大家都懂的。
                 出现这种问题是因为MyEclipse自动生成变量XXX的get和set方法时，
                      如果变量的类型是boolean，那么get方法的名字就是isXXX，
                    如果变量名为isXXX，那么get方法的名字和变量名相同也是isXXX。

	 */
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private boolean isAdmin;
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
	@NotEmpty(message="用户名不能为空")
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	@NotEmpty(message="用户密码不能为空")
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
	@Email(message="邮件格式不正确")
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	//一定要写成getXXX的形式
	@Type(type="yes_no")
	public boolean getisAdmin()
	{
		return isAdmin;
	}
	public void setisAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString()
	{
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + ", email=" + email
				+ ", isAdmin=" + isAdmin + "]";
	}
	
	
}

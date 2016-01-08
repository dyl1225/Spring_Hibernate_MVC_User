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
	 * ������Ը�����
       �и���ΪisAdmin��boolean���͵ı�����jspҳ���ȡ����ֵΪ�գ�
                  ���������û��ȡ�����߱��������ڵ�״������������Action�������Ǹ�ֵ��false�ˡ�
              ��������һ���е�ͬѧҲ���������������⣬����ܺý��������������isAdmin��get������
                   ������isAdmin���ĳ�getIsAdmin�ͺ��ˡ�ԭ���Ҷ����ġ�
                 ����������������ΪMyEclipse�Զ����ɱ���XXX��get��set����ʱ��
                      ���������������boolean����ôget���������־���isXXX��
                    ���������ΪisXXX����ôget���������ֺͱ�������ͬҲ��isXXX��

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
	@NotEmpty(message="�û�������Ϊ��")
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	@NotEmpty(message="�û����벻��Ϊ��")
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
	@Email(message="�ʼ���ʽ����ȷ")
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	//һ��Ҫд��getXXX����ʽ
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

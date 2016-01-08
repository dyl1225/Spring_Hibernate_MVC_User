package com.dyl.dao.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.dyl.dao.UserDao;
import com.dyl.model.Pager;
import com.dyl.model.SystemContext;
import com.dyl.model.User;
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao
{

	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}

	public void add(User user)
	{
		this.hibernateTemplate.save(user);
	}

	public void update(User user)
	{
		this.hibernateTemplate.update(user);
	}

	public void delete(int id)
	{
		User user=this.load(id);
		this.hibernateTemplate.delete(user);
	}

	public User load(int id)
	{
		return this.hibernateTemplate.load(User.class, id);
	}
	public User loadUserByName(String username)
	{
		
		return (User) this.hibernateTemplate.getSessionFactory().getCurrentSession().
				createQuery("from User where username=?")
				.setParameter(0, username).uniqueResult();
	}
	public User checkUser(String username, String password)
	{
		
		return (User) this.hibernateTemplate.getSessionFactory().getCurrentSession().
				createQuery("from User where username=? and password=?")
				.setParameter(0, username).setParameter(1, password).uniqueResult();
		
	}
	@SuppressWarnings("unchecked")
	public List<User> list()
	{
		return this.hibernateTemplate.find("from User");
	}

	@SuppressWarnings("unchecked")
	public Pager<User> find()
	{
		 int size=SystemContext.getSize();//每页显示的页数
		 int offset=SystemContext.getOffset();//起始位置
		 long total=(Long) this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("select count(*) from User").uniqueResult();
		 Query query=this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from User");
		 query.setFirstResult(offset);
		 query.setMaxResults(size);
		 List<User> list = query.list();     
	
		
		Pager<User> page=new Pager<User>();
		page.setDatas(list);
		page.setOffset(offset);
		page.setSize(size);
		page.setTotal(total);
		return page;
	}
	
	

}

package com.dyl.model;

public class SystemContext
{
	private static ThreadLocal<Integer> offset=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> size=new ThreadLocal<Integer>();
	public static int DEFAULT_PAGE_SIZE= 10;
	public static Integer getOffset()
	{
		return offset.get();
	}
	public static void setOffset(Integer _offset)
	{
		offset.set(_offset);
	}
	public static void removeOffSet()
	{
		offset.remove();
	}
	public static Integer getSize()
	{
		Integer _pageSIze= size.get();
		if(_pageSIze==null)
		{
			_pageSIze=DEFAULT_PAGE_SIZE;
		}
		return _pageSIze;
	}
	public static void setSize(Integer _size)
	{
		size.set(_size);
	}
	public static void removeOffSize()
	{
		size.remove();
	}
}

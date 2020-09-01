package com.oracle.gdms.util;

import java.util.ResourceBundle;


public class Factory {
	
	private static ResourceBundle rb; //定义一个资源绑定对象
	
	static {
		rb = ResourceBundle.getBundle("config/application");//注意路径
	}
    //构造私有方法
	private Factory() {}
	// 指向自己实例的私有静态引用，主动创建
	private static Factory fac = null;
    // 以自己实例为返回值的静态的公有方法，静态工厂方法
	public static Factory getInstance() {
		// TODO 自动生成的方法存根
	fac = fac ==null ? new Factory() : fac;
		
		return fac;
	}

	public Object getObject(String key) {
		// 读取配置文件，从配置文件中找到key对应的class路径和名称
		String classname = rb.getString(key);
		Object o = null;
		try {
			o = Class.forName(classname).newInstance();
		} catch (InstantiationException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return o;
	}

}

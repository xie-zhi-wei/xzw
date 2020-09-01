package com.oracle.gdms.util;

import java.util.ResourceBundle;


public class Factory {
	
	private static ResourceBundle rb; //����һ����Դ�󶨶���
	
	static {
		rb = ResourceBundle.getBundle("config/application");//ע��·��
	}
    //����˽�з���
	private Factory() {}
	// ָ���Լ�ʵ����˽�о�̬���ã���������
	private static Factory fac = null;
    // ���Լ�ʵ��Ϊ����ֵ�ľ�̬�Ĺ��з�������̬��������
	public static Factory getInstance() {
		// TODO �Զ����ɵķ������
	fac = fac ==null ? new Factory() : fac;
		
		return fac;
	}

	public Object getObject(String key) {
		// ��ȡ�����ļ����������ļ����ҵ�key��Ӧ��class·��������
		String classname = rb.getString(key);
		Object o = null;
		try {
			o = Class.forName(classname).newInstance();
		} catch (InstantiationException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return o;
	}

}

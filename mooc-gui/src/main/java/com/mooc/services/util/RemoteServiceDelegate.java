package com.mooc.services.util;

public class RemoteServiceDelegate {

	public static <T> T get(Class clazz) {
		return (T) ServiceLocator.getInstance().
				getProxy("/mooc-ear/mooc-ejb/" + clazz.getSimpleName()
				+ "Impl!com.mooc.services." + clazz.getSimpleName());
		
	}

}

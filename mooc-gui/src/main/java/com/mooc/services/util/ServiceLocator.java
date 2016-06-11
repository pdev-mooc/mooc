package com.mooc.services.util;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

	private static ServiceLocator instance;
	private Map<String, Object> cache;
	private Context context;

	public static synchronized ServiceLocator getInstance() {
		if (instance == null) {
			return new ServiceLocator();
		}
		return instance;
	}

	public ServiceLocator() {
		cache = new HashMap<String, Object>();
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public synchronized Object getProxy(String jndiName) {
		Object proxy = null;
		proxy = cache.get(jndiName);
		if (proxy == null) {
			try {
				proxy = context.lookup(jndiName);
				cache.put(jndiName, proxy);
			} catch (NamingException e) {
				e.printStackTrace();
			}

		}
		return proxy;
	}

}

package org.mobicents.servlet.sip.weld.environment.msstomcat7;

import java.lang.reflect.InvocationTargetException;

import javax.naming.NamingException;

import org.apache.tomcat.InstanceManager;
import org.jboss.weld.environment.tomcat7.ForwardingInstanceManager;
import org.mobicents.servlet.sip.annotations.SipInstanceManager;

public abstract class SipForwardingInstanceManager extends ForwardingInstanceManager implements SipInstanceManager{

	   protected abstract InstanceManager delegate();

	   public void destroyInstance(Object o) throws IllegalAccessException, InvocationTargetException
	   {
	      delegate().destroyInstance(o);

	   }

	   public void newInstance(Object o) throws IllegalAccessException, InvocationTargetException, NamingException
	   {
	      delegate().newInstance(o);
	   }

	   public Object newInstance(String fqcn, ClassLoader classLoader) throws IllegalAccessException, InvocationTargetException, NamingException, InstantiationException, ClassNotFoundException
	   {
	      return delegate().newInstance(fqcn, classLoader);
	   }

	   public Object newInstance(String fqcn) throws IllegalAccessException, InvocationTargetException, NamingException, InstantiationException, ClassNotFoundException
	   {
	      return delegate().newInstance(fqcn);
	   }
}
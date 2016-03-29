package com.tibco.devtools.builder.test;

import java.lang.reflect.Method;


public class TestObfuscation
{

	public static void main( String[] args )
	{
		try {
			TestObfuscation test = new TestObfuscation();
			Method method = test .getClass() .getDeclaredMethod( "publicMethod", new Class[0] );
			method .invoke( test, new Object[0] );
			System .err .println( "SUCCESS: able to invoke publicMethod" );
		} catch ( Exception e )
		{
			System .err .println( "FAILURE: unable to invoke publicMethod" );
		}
		try {
			TestObfuscation test = new TestObfuscation();
			Method method = test .getClass() .getDeclaredMethod( "protectedMethod", new Class[0] );
			method .invoke( test, new Object[0] );
			System .err .println( "SUCCESS: able to invoke protectedMethod" );
		} catch ( Exception e )
		{
			System .err .println( "FAILURE: unable to invoke protectedMethod" );
		}
		try {
			TestObfuscation test = new TestObfuscation();
			Method method = test .getClass() .getDeclaredMethod( "packagePrivate", new Class[0] );
			method .invoke( test, new Object[0] );
			System .err .println( "SUCCESS: able to invoke packagePrivate" );
		} catch ( Exception e )
		{
			System .err .println( "FAILURE: unable to invoke packagePrivate" );
		}
		try {
			TestObfuscation test = new TestObfuscation();
			Method method = test .getClass() .getDeclaredMethod( "privateMethod", new Class[0] );
			method .invoke( test, new Object[0] );
			System .err .println( "FAILURE: able to invoke privateMethod" );
		} catch ( Exception e )
		{
			System .err .println( "SUCCESS: unable to invoke privateMethod" );
		}
	}
	
	void packagePrivate() {}
	
	public void publicMethod() {}
	
	protected void protectedMethod() {}
	
	private void privateMethod() {}
	
}

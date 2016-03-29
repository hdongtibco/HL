/*
 *(c) Copyright 2008, TIBCO Software Inc.  All rights reserved.
 *
 * LEGAL NOTICE:  This source code is provided to specific authorized end
 * users pursuant to a separate license agreement.  You MAY NOT use this
 * source code if you do not have a separate license from TIBCO Software
 * Inc.  Except as expressly set forth in such license agreement, this
 * source code, or any portion thereof, may not be used, modified,
 * reproduced, transmitted, or distributed in any form or by any means,
 * electronic or mechanical, without written permission from
 * TIBCO Software Inc.
 */
 
package com.tibco.bw.palette.clarity.runtime;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

public class StartBatchTest {
	String expectedResult = null;
	
	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	@Before
	public void setUp() throws Exception {
		// begin-custom-code
		// add your own business code here
		expectedResult = "result";
		// end-custom-code
	}
	
	
	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	@After
	public void tearDown() throws Exception {
		// begin-custom-code
		// add your own business code here
		
		// end-custom-code
	}
	
	/**
	 * <!-- begin-custom-doc -->
	 * 
	 * <!-- end-custom-doc -->
	 * @generated
	 */
	@Test
	public void testBusiness(){
		// begin-custom-code
		// add your own business code here
	    String actualResult = "result";
	    Assert.assertEquals(expectedResult,actualResult);
        
		// end-custom-code
	}

}

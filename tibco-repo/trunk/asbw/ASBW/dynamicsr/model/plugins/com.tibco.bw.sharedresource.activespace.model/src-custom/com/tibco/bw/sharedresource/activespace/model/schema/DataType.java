//(c) Copyright 2012, TIBCO Software Inc.  All rights reserved.
//LEGAL NOTICE:  This source code is provided to specific authorized end
//users pursuant to a separate license agreement.  You MAY NOT use this
//source code if you do not have a separate license from TIBCO Software
//Inc.  Except as expressly set forth in such license agreement, this
//source code, or any portion thereof, may not be used, modified,
//reproduced, transmitted, or distributed in any form or by any means,
//electronic or mechanical, without written permission from  TIBCO
//Software Inc.
package com.tibco.bw.sharedresource.activespace.model.schema;

public enum DataType {

	BOOLEAN {
		@Override
		public String getName() {
			return "boolean";
		}

		@Override
		public Class<?> getTypeClass() {
			return Boolean.class;
		}
		
	},
	
	STRING {
		@Override
		public String getName() {
			return "string";
		}
		
		@Override
		public Class<?> getTypeClass() {
			return String.class;
		}
	},
	
	INTEGER {
		@Override
		public String getName() {
			return "integer";
		}
		
		@Override
		public Class<?> getTypeClass() {
			return Integer.class;
		}
	},
	
	ENUM {
		@Override
		public String getName() {
			return "enumeration";
		}

		@Override
		public Class<?> getTypeClass() {
			return null;
		}
		
	},
	
	LONG {
		@Override
		public String getName() {
			return "long";
		}

		@Override
		public Class<?> getTypeClass() {
			// TODO Auto-generated method stub
			return Long.class;
		}
	},
	
	ARRAY {
		@Override
		public String getName() {
			return "string[]";
		}

		@Override
		public Class<?> getTypeClass() {
			// TODO Auto-generated method stub
			return String[].class;
		}
	},
	
	PASSWORD {
		@Override
		public String getName() {
			return "password";
		}

		@Override
		public Class<?> getTypeClass() {
			// TODO Auto-generated method stub
			return char[].class;
		}
	},
	
	LABEL {

		@Override
		public String getName() {
			return "label";
		}

		@Override
		public Class<?> getTypeClass() {
			return null;
		}
		
	};
	
	public abstract String getName();
	public abstract Class<?> getTypeClass();

	public static DataType getDataTypeByName(String name) {
		DataType[] types = DataType.values();
		for (DataType type : types) {
			if (type.getName().equalsIgnoreCase(name)) {
				return type;
			}
		}
		return null;
	}
}

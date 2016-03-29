//(c) Copyright 2012, TIBCO Software Inc.  All rights reserved.
//LEGAL NOTICE:  This source code is provided to specific authorized end
//users pursuant to a separate license agreement.  You MAY NOT use this
//source code if you do not have a separate license from TIBCO Software
//Inc.  Except as expressly set forth in such license agreement, this
//source code, or any portion thereof, may not be used, modified,
//reproduced, transmitted, or distributed in any form or by any means,
//electronic or mechanical, without written permission from  TIBCO
//Software Inc.
package schema;

public enum Definition {

	FIELD_DEF {

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "FieldDef";
		}
	},

	INDEX_DEF {

		@Override
		public String getName() {
			return "IndexDef";
		}

	},

	KEY_DEF {

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "KeyDef";
		}

	},
	
	SPACE_DEF {

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "SpaceDef";
		}
		
	},

	MEMBER_DEF {

		@Override
		public String getName() {
			return "MemberDef";
		}

	};
	
	public abstract String getName();

    public static Definition getDataTypeByName(String name) {
        Definition[] types = Definition.values();
        for (Definition type : types) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

}

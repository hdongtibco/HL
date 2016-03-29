package com.tibco.bw.palette.activespace.runtime.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.genxdm.Model;
import org.genxdm.ProcessingContext;
import org.genxdm.mutable.MutableModel;
import org.genxdm.mutable.NodeFactory;
import org.genxdm.typed.TypedContext;
import org.genxdm.typed.types.AtomBridge;

import com.tibco.as.space.ASException;
import com.tibco.as.space.ASStatus;
import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.KeyDef;
import com.tibco.as.space.Tuple;
import com.tibco.bw.palette.activespace.runtime.helper.Utils;
import com.tibco.bw.sharedresource.activespace.runtime.space.SpaceResource;
/**
 * 
 * @author Andy
 * 
 */
public class ASConverter {
	public enum TypeName {
		INT, SHORT, STRING, FLOAT, DOUBLE, DATETIME, CHAR, INTEGER, LONG, BOOLEAN, BLOB;
	}
	
	public static <N, A> N tuple2N(Tuple tuple, ProcessingContext<N> pcx, NodeFactory<N> noteFactory) throws ASException
	{

		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext( null );
		AtomBridge<A> atomBridge = tc.getAtomBridge();
		A atom = null;
		
		N tupleN = noteFactory.createElement( "" , "Tuple" , "" );

		for ( String fieldName : tuple.keySet() )
		{
			Object fieldValue = tuple.get( fieldName );
			
			N nameNodeValue = noteFactory.createText( fieldName );
			N valueNodeValue = null ;
			N fieldNode = null ;
			N nameNode = null ;
			N valueNode = null ;
			
			if ( fieldValue instanceof Integer )
			{
				fieldNode = noteFactory.createElement( "" , "INTEGER" , "" ) ;
				
				atom = atomBridge.createInteger( tuple.getInt( String.valueOf( fieldName)) );
				valueNodeValue = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			}
			else if ( fieldValue instanceof Boolean )
			{
				fieldNode = noteFactory.createElement( "" , "BOOLEAN" , "" ) ;
				
				atom = atomBridge.createBoolean( tuple.getBoolean( String.valueOf( fieldName)) );
				valueNodeValue = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			}
			else if ( fieldValue instanceof Double )
			{
				fieldNode = noteFactory.createElement( "" , "DOUBLE" , "" ) ;
				atom = atomBridge.createDouble( tuple.getDouble( String.valueOf(fieldName) ) );
				valueNodeValue = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			}
			else if ( fieldValue instanceof Float )
			{
				fieldNode = noteFactory.createElement( "" , "FLOAT" , "" ) ;
				atom = atomBridge.createFloat( tuple.getFloat( String.valueOf(fieldName )) );
				valueNodeValue = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			}
			else if ( fieldValue instanceof Long )
			{
				fieldNode = noteFactory.createElement( "" , "LONG" , "" ) ;
				atom = atomBridge.createLong( tuple.getLong( String.valueOf( fieldName) ) );
				valueNodeValue = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			}
			else if ( fieldValue instanceof Short )
			{
				fieldNode = noteFactory.createElement( "" , "SHORT" , "" ) ;
				atom = atomBridge.createShort( tuple.getShort( String.valueOf(fieldName ) ));
				valueNodeValue = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			}
			else if ( fieldValue instanceof byte[] )
			{
				fieldNode = noteFactory.createElement( "" , "BLOB" , "" ) ;
				atom = atomBridge.createBase64Binary(tuple.getBlob( String.valueOf(fieldName)));
//				valueNodeValue = noteFactory.createText( new String( tuple.getBlob( String.valueOf(fieldName) ) ) );
				valueNodeValue = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			}
			else if ( fieldValue instanceof DateTime )
			{
				fieldNode = noteFactory.createElement( "" , "DATETIME" , "" ) ;
				DateTime dateTime = tuple.getDateTime( String.valueOf(fieldName ));
				Calendar calendar = dateTime.getTime();
				atom = atomBridge.createDateTime( calendar.get( Calendar.YEAR ) , calendar.get( Calendar.MONTH ) + 1 , calendar.get( Calendar.DATE ) , calendar.get( Calendar.HOUR_OF_DAY ) , calendar.get( Calendar.MINUTE ) , calendar.get( Calendar.SECOND ) , calendar.get( Calendar.MILLISECOND ) , null , calendar.get( Calendar.DST_OFFSET ) );
				valueNodeValue = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			}
			else if ( fieldValue instanceof Character )
			{
				fieldNode = noteFactory.createElement( "" , "CHAR" , "" ) ;
				atom = atomBridge.createDouble( tuple.getChar( String.valueOf(fieldName )) );
				valueNodeValue = noteFactory.createText( atomBridge.getC14NForm( atom ) );
			}
			else if ( fieldValue instanceof String )
			{
				fieldNode = noteFactory.createElement( "" , "STRING" , "" ) ;
				valueNodeValue = noteFactory.createText( tuple.getString( String.valueOf(fieldName) ) );
			}
			
			nameNode = noteFactory.createElement( "" , "name" , "" ) ;
			valueNode = noteFactory.createElement( "" , "value" , "" ) ;
			mutableModel.appendChild( fieldNode , nameNode ) ;
			mutableModel.appendChild( fieldNode , valueNode ) ;
			
			mutableModel.appendChild( nameNode , nameNodeValue ) ;
			mutableModel.appendChild( valueNode , valueNodeValue ) ;
			
			mutableModel.appendChild( tupleN , fieldNode ) ;
		}
		
		return tupleN;
	}
	
	public static <N, A> N tuple2N (Tuple tuple, ProcessingContext<N> pcx, NodeFactory<N> noteFactory, Collection<FieldDef> fieldDefs) throws ASException {
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext(null);
		AtomBridge<A> atomBridge = tc.getAtomBridge();
		A atom = null;
		
		N tupleN = noteFactory.createElement("", "Tuple", "");

		for (FieldDef fieldDef : fieldDefs) {
			Object value = tuple.get(fieldDef.getName());
			if(value != null){
				N field = noteFactory.createElement("", fieldDef.getName(),"");
				mutableModel.appendChild(tupleN, field);
				String valueType = fieldDef.getType().toString();
				N valueNode = null;
				switch (TypeName.valueOf(valueType.toUpperCase())) {
				case INTEGER:
					atom = atomBridge.createInt(tuple.getInt(fieldDef.getName()).intValue());
					valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
					break;
				case STRING:
					valueNode = noteFactory.createText(tuple.getString(fieldDef.getName()));
					break;
				case BOOLEAN:
					atom = atomBridge.createBoolean(tuple.getBoolean(fieldDef.getName()));
					valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
					break;
				case DOUBLE:
					atom = atomBridge.createDouble(tuple.getDouble(fieldDef.getName()));
					valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
					break;	
				case FLOAT:
					atom = atomBridge.createFloat(tuple.getFloat(fieldDef.getName()));
					valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
					break;
				case LONG:
					atom = atomBridge.createLong(tuple.getLong(fieldDef.getName()));
					valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
					break;
				case SHORT:
					atom = atomBridge.createShort(tuple.getShort(fieldDef.getName()));
					valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
					break;
				case CHAR:
					valueNode = noteFactory.createText(tuple.getChar(fieldDef.getName()).toString());
					break;
				case BLOB:
					valueNode = noteFactory.createText(new String(tuple.getBlob(fieldDef.getName())));
					break;
				case DATETIME:
					DateTime dateTime = tuple.getDateTime(fieldDef.getName());
                    Calendar calendar = dateTime.getTime();
                    atom = atomBridge.createDateTime(calendar.get(Calendar.YEAR)
														, calendar.get(Calendar.MONTH) + 1
														, calendar.get(Calendar.DATE)
														, calendar.get(Calendar.HOUR_OF_DAY)
														, calendar.get(Calendar.MINUTE)
														, calendar.get(Calendar.SECOND)
														, calendar.get(Calendar.MILLISECOND)
														, null
														, calendar.get(Calendar.DST_OFFSET));
					valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
					break;
				}
				mutableModel.appendChild(field, valueNode);
			}else{
				 if (!fieldDef.isNullable()) {
	                    throw new ASException(ASStatus.SYS_ERROR, "Field value is null, but it's NOT defined as nullable.");
	             }
			}
		}
		return tupleN;
	}
	
	public static <N, A> N tupleToN (Tuple tuple, ProcessingContext<N> pcx, NodeFactory<N> noteFactory, Collection<FieldDef> fieldDefs,Collection<KeyDef> keyDefs) throws ASException {
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext(null);
		AtomBridge<A> atomBridge = tc.getAtomBridge();
		A atom = null;
		
		N tupleN = noteFactory.createElement("", "Tuple", "");

		for (FieldDef fieldDef : fieldDefs) {
			for(KeyDef keyDef : keyDefs){
				for(String fieldName :keyDef.getFieldNames()){
					if(fieldName.equals(fieldDef.getName())){
						Object value = tuple.get(fieldDef.getName());
						//FieldDef::{FieldDef name=id, type=INTEGER, isNullable=false, isEncrypted=false pos=0}
						if(value != null ){
							N field = noteFactory.createElement("", fieldDef.getName(),"");//dm:node-kind()="element", dm:node-name()="id", dm:string-value()=""
							mutableModel.appendChild(tupleN, field);
							String valueType = fieldDef.getType().toString();
							N valueNode = null;
							switch (TypeName.valueOf(valueType.toUpperCase())) {
							case INTEGER:
								atom = atomBridge.createInt(tuple.getInt(fieldDef.getName()).intValue());//   1
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));//dm:node-kind()="text", dm:node-name()="null", dm:string-value()="1"
								break;
							case STRING:
								valueNode = noteFactory.createText(tuple.getString(fieldDef.getName()));
								break;
							case BOOLEAN:
								atom = atomBridge.createBoolean(tuple.getBoolean(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							case DOUBLE:
								atom = atomBridge.createDouble(tuple.getDouble(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;	
							case FLOAT:
								atom = atomBridge.createFloat(tuple.getFloat(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							case LONG:
								atom = atomBridge.createLong(tuple.getLong(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							case SHORT:
								atom = atomBridge.createShort(tuple.getShort(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							case CHAR:
								valueNode = noteFactory.createText(tuple.getChar(fieldDef.getName()).toString());
								break;
							case BLOB:
								valueNode = noteFactory.createText(new String(tuple.getBlob(fieldDef.getName())));
								break;
							case DATETIME:
								DateTime dateTime = tuple.getDateTime(fieldDef.getName());
			                    Calendar calendar = dateTime.getTime();
			                    atom = atomBridge.createDateTime(calendar.get(Calendar.YEAR)
																	, calendar.get(Calendar.MONTH) + 1
																	, calendar.get(Calendar.DATE)
																	, calendar.get(Calendar.HOUR_OF_DAY)
																	, calendar.get(Calendar.MINUTE)
																	, calendar.get(Calendar.SECOND)
																	, calendar.get(Calendar.MILLISECOND)
																	, null
																	, calendar.get(Calendar.DST_OFFSET));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							}
							mutableModel.appendChild(field, valueNode);
					}else{
						
						if (!fieldDef.isNullable()) {
			                    throw new ASException(ASStatus.SYS_ERROR, "Field value is null, but it's NOT defined as nullable.");
			             }
					}
				}
			}
			}
			
		}
		return tupleN;
	}
	
	public static <N> Tuple n2Tuple(N inputData, final ProcessingContext<N> pcx, Collection<FieldDef> fieldDefs)
			throws Exception {
		Model<N> model = pcx.getModel();
		Tuple tuple = Tuple.create();
		for (FieldDef fieldDef : fieldDefs) {
			String name = fieldDef.getName();
			N field = model.getFirstChildElementByName(inputData, null, name);
			if (field != null) {
				String value = model.getStringValue(field);
				if (fieldDef.getType() != null) {
					String valueType = fieldDef.getType().toString();
					if(!valueType.equalsIgnoreCase("string") && value.equals("INF")) {
						throw new NumberFormatException();
					}
					switch (TypeName.valueOf(valueType.toUpperCase())) {
					case INTEGER:
						int intValue = Integer.parseInt(value);
						tuple.putInt(fieldDef.getName(), intValue);
						break;
					case STRING:
						tuple.putString(fieldDef.getName(), value);
						break;
					case DOUBLE:
						double doubleValue = Double.parseDouble(value);
						tuple.putDouble(fieldDef.getName(), doubleValue);
						break;
					case FLOAT:
						float floatValue = Float.parseFloat(value);
						tuple.putFloat(fieldDef.getName(), floatValue);
						break;
					case BOOLEAN:
						boolean booleanValue = Boolean.parseBoolean(value);
						tuple.putBoolean(fieldDef.getName(), booleanValue);
						break;	
					case LONG:
						long longValue = Long.parseLong(value);
						tuple.putLong(fieldDef.getName(), longValue);
						break;
					case SHORT:
						short shortValue = Short.parseShort(value);
						tuple.putShort(fieldDef.getName(), shortValue);
						break;
					case CHAR:
						char charValue = ((String) value).charAt(0);
						tuple.putChar(fieldDef.getName(), charValue);
						break;
					case BLOB:
						tuple.putBlob(name, value.getBytes());
						break;
					case DATETIME:
				        Date date = Utils.getFormatDate(value);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						DateTime dateTime = DateTime.create(calendar.getTimeInMillis());
						tuple.putDateTime(name, dateTime);
						break;
					}
				}
			}
		}
		return tuple;
	}

	public static <N, A> N tuple2ContextN(Tuple tuple, ProcessingContext<N> pcx , NodeFactory<N> noteFactory , String contextTagName ){
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext( null );
		AtomBridge<A> atomBridge = tc.getAtomBridge();
		A atom = null;
		
		N tupleN = noteFactory.createElement( "" , Utils.isNotEmpty(contextTagName)?contextTagName:"ContextTuple" , "" );

		for ( String fieldName : tuple.keySet() )
		{
			Object fieldValue = tuple.get( fieldName );
			
			N nameNodeValue = noteFactory.createText( fieldName );
			N fieldNode = null ;
			N nameNode = null ;
			N valueNode = null ;
			
			String valueType = TypeName.STRING.name() ;
		
			if(fieldValue instanceof Integer){
				valueType = TypeName.INTEGER.name();
				atom = atomBridge.createInt(tuple.getInt(fieldName).intValue());
				valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
			} else if( fieldValue instanceof String){
				valueType = TypeName.STRING.name();
				valueNode = noteFactory.createText(tuple.getString(fieldName));
			}else if( fieldValue instanceof Boolean){
				valueType = TypeName.BOOLEAN.name();
				atom = atomBridge.createBoolean(tuple.getBoolean(fieldName));
				valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
			}else if( fieldValue instanceof Double){
				valueType = TypeName.DOUBLE.name();
				atom = atomBridge.createDouble(tuple.getDouble(fieldName));
				valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
			}else if( fieldValue instanceof Float){
				valueType = TypeName.FLOAT.name();
				atom = atomBridge.createFloat(tuple.getFloat(fieldName));
				valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
			}else if( fieldValue instanceof Long){
				valueType = TypeName.LONG.name();
				atom = atomBridge.createLong(tuple.getLong(fieldName));
				valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
			}else if(fieldValue instanceof Short){
				valueType = TypeName.SHORT.name();
				atom = atomBridge.createShort(tuple.getShort(fieldName));
				valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
			}else if(fieldValue instanceof Character){
				valueType = TypeName.CHAR.name();
				valueNode = noteFactory.createText(tuple.getChar(fieldName).toString());
			}else if( fieldValue instanceof byte[]){
				valueType = TypeName.BLOB.name();
				atom = atomBridge.createBase64Binary(tuple.getBlob(fieldName));
				valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
			}else if( fieldValue instanceof DateTime){
				valueType = TypeName.DATETIME.name();
				DateTime dateTime = tuple.getDateTime(fieldName);
                Calendar calendar = dateTime.getTime();
                atom = atomBridge.createDateTime(calendar.get(Calendar.YEAR)
													, calendar.get(Calendar.MONTH) + 1
													, calendar.get(Calendar.DATE)
													, calendar.get(Calendar.HOUR_OF_DAY)
													, calendar.get(Calendar.MINUTE)
													, calendar.get(Calendar.SECOND)
													, calendar.get(Calendar.MILLISECOND)
													, null
													, calendar.get(Calendar.DST_OFFSET));
				valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
			}
			
			fieldNode = noteFactory.createElement( "" , valueType , "" ) ;
			
			nameNode = noteFactory.createElement( "" , "name" , "" ) ;
			N valueNodeTag = noteFactory.createElement( "" , "value" , "" ) ;
			mutableModel.appendChild( fieldNode , nameNode ) ;
			mutableModel.appendChild( fieldNode , valueNodeTag ) ;
			
			mutableModel.appendChild( nameNode , nameNodeValue ) ;
			
			if(fieldValue != null) {
				mutableModel.appendChild( valueNodeTag , valueNode ) ;
				mutableModel.appendChild( tupleN , fieldNode ) ;
			}
			
		}
		
		return tupleN;
	}
		
	public static <N, A> N keyTuple2N (Tuple tuple, ProcessingContext<N> pcx, NodeFactory<N> noteFactory, SpaceResource resource ,String tupleTagName) throws ASException {
		MutableModel<N> mutableModel = pcx.getMutableContext().getModel();
		TypedContext<N, A> tc = pcx.getTypedContext(null);
		AtomBridge<A> atomBridge = tc.getAtomBridge();
		A atom = null;
		
		N tupleN = noteFactory.createElement("", Utils.isEmpty(tupleTagName)?"KeyTuple":tupleTagName, "");
		Collection<FieldDef> fieldDefs = resource.getFieldDefs();
		Collection<KeyDef> keyDefs = resource.getKeyDefs();
		for(KeyDef keys : keyDefs){
			for(String keyName : keys.getFieldNames()){				
				for (FieldDef fieldDef : fieldDefs) {
					if( keyName.equals(fieldDef.getName())){
						Object value = tuple.get(fieldDef.getName());
						if(value != null){
							N field = noteFactory.createElement("", fieldDef.getName(),"");
							mutableModel.appendChild(tupleN, field);
							String valueType = fieldDef.getType().toString();
							N valueNode = null;
							switch (TypeName.valueOf(valueType.toUpperCase())) {
							case INTEGER:
								atom = atomBridge.createInt(tuple.getInt(fieldDef.getName()).intValue());
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							case STRING:
								valueNode = noteFactory.createText(tuple.getString(fieldDef.getName()));
								break;
							case BOOLEAN:
								atom = atomBridge.createBoolean(tuple.getBoolean(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							case DOUBLE:
								atom = atomBridge.createDouble(tuple.getDouble(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;	
							case FLOAT:
								atom = atomBridge.createFloat(tuple.getFloat(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							case LONG:
								atom = atomBridge.createLong(tuple.getLong(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							case SHORT:
								atom = atomBridge.createShort(tuple.getShort(fieldDef.getName()));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							case CHAR:
								valueNode = noteFactory.createText(tuple.getChar(fieldDef.getName()).toString());
								break;
							case BLOB:
								valueNode = noteFactory.createText(new String(tuple.getBlob(fieldDef.getName())));
								break;
							case DATETIME:
								DateTime dateTime = tuple.getDateTime(fieldDef.getName());
			                    Calendar calendar = dateTime.getTime();
			                    atom = atomBridge.createDateTime(calendar.get(Calendar.YEAR)
																	, calendar.get(Calendar.MONTH) + 1
																	, calendar.get(Calendar.DATE)
																	, calendar.get(Calendar.HOUR_OF_DAY)
																	, calendar.get(Calendar.MINUTE)
																	, calendar.get(Calendar.SECOND)
																	, calendar.get(Calendar.MILLISECOND)
																	, null
																	, calendar.get(Calendar.DST_OFFSET));
								valueNode = noteFactory.createText(atomBridge.getC14NForm(atom));
								break;
							}
							mutableModel.appendChild(field, valueNode);
						}else{
							 if (!fieldDef.isNullable()) {
				                    throw new ASException(ASStatus.SYS_ERROR, "Field value is null, but it's NOT defined as nullable.");
				             }
						}
					}
				}	
			}
		}
		
		return tupleN;
	}
	
	public static <N> Tuple contextN2Tuple(N inputData , final ProcessingContext<N> pcx , String tagName ){
		Model<N> model = pcx.getModel();
		
		N  contextData = model.getFirstChildElementByName(inputData, null, tagName);
		if(null == contextData) return null;
			
		Tuple tuple = Tuple.create();
		Iterable<N> childElements = model.getChildElements(contextData);
		
		for (Iterator<N> child = childElements.iterator(); child.hasNext();) {
			N elem = child.next();
			String name = model.getLocalName(elem);
			
			N nameTag = model.getFirstChildElementByName(elem, null, "name");
			String eleName = model.getStringValue(nameTag);
			N valueTag = model.getFirstChildElementByName(elem, null, "value");
			String eleValue = model.getStringValue(valueTag);
			
			tuple.put(eleName, convertValueType(name, eleValue));
		}
		
//		for(TypeName type : ASConverter.TypeName.values()){
//			Iterable<N> typeCollection = model.getChildElementsByName(contextData, null, type.name());
//			for(N elem : typeCollection){
//				N nameTag = model.getFirstChildElementByName(elem, null, "Name");
//				String eleName = model.getStringValue(nameTag);
//				N valueTag = model.getFirstChildElementByName(elem, null, "Value");
//				
//				String eleValue = model.getStringValue(valueTag);
//				tuple.put(eleName, convertValueType(type.name() , eleValue));
//			}
//		}
		
		return tuple ;
	}
	
	
	private static Object convertValueType(String valueType, String value) {
		if(TypeName.STRING.name().equalsIgnoreCase(valueType)){
			return value;
		}else if(TypeName.INTEGER.name().equalsIgnoreCase(valueType)){
			return Integer.parseInt(value);
		}else if(TypeName.DOUBLE.name().equalsIgnoreCase(valueType)){
			return Double.parseDouble(value);
		}else if(TypeName.FLOAT.name().equalsIgnoreCase(valueType)){
			return Float.parseFloat(value);
		}else if(TypeName.BOOLEAN.name().equalsIgnoreCase(valueType)){
			return Boolean.parseBoolean(value);
		}else if(TypeName.LONG.name().equalsIgnoreCase(valueType)){
			return Long.parseLong(value);
		}else if(TypeName.SHORT.name().equalsIgnoreCase(valueType)){
			return Short.parseShort(value);
		}else if(TypeName.CHAR.name().equalsIgnoreCase(valueType)){
			return ((String) value).charAt(0);
		}else if(TypeName.BLOB.name().equalsIgnoreCase(valueType)){
			return value.getBytes();
		}else if(TypeName.DATETIME.name().equalsIgnoreCase(valueType)){
			 Date date = null;
			try {
				date = Utils.getFormatDate(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 Calendar calendar = Calendar.getInstance();
			 calendar.setTime(date);
			 DateTime dateTime = DateTime.create(calendar.getTimeInMillis());
			 return dateTime;
		}else{
			return value;
		}
	
	}
	
}


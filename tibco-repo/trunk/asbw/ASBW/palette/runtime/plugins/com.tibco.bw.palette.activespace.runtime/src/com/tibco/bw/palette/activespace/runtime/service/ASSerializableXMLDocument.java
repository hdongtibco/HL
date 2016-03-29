package com.tibco.bw.palette.activespace.runtime.service;

import org.genxdm.ProcessingContext;

import com.tibco.as.space.Context;
import com.tibco.as.space.Metaspace;
import com.tibco.bw.runtime.util.SerializableXMLDocument;

public class ASSerializableXMLDocument<N> extends SerializableXMLDocument<N> {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1327779938266060532L;
	
	private Context asContext = null;
	private Metaspace ms = null;
	private EntryIterator ei = null;
	
	public ASSerializableXMLDocument(ProcessingContext<N> processingContext, N result, Context asContext, Metaspace ms, EntryIterator ei) {
		this(processingContext, result, asContext, ms);
		this.ei = ei;
	}
	
	public ASSerializableXMLDocument(ProcessingContext<N> processingContext, N result, Context asContext, Metaspace ms) {
		super(processingContext, result);
		this.asContext = asContext;
		this.ms = ms;
	}
	
	public Context getASContext() {
		return this.asContext;
	}
	
	public Metaspace getMetaspace() {
		return this.ms;
	}
	
	public EntryIterator getEntryIterator() {
		return this.ei;
	}
}

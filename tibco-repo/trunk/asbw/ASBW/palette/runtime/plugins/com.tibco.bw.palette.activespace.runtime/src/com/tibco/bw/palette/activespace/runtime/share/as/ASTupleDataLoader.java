package com.tibco.bw.palette.activespace.runtime.share.as;

import java.util.ArrayList;
import java.util.Collection;

import com.tibco.as.space.Tuple;

public class ASTupleDataLoader<T> {
	private Collection<Tuple> tuplesList = new ArrayList<Tuple>();

	private Collection<T> optionsList = new ArrayList<T>();

	public Collection<Tuple> getTuplesList() {
		return tuplesList;
	}

	public void setTuplesList(Collection<Tuple> tuplesList) {
		tuplesList.addAll(tuplesList);
	}

	public Collection<T> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(Collection<T> optionsList) {
		this.optionsList.addAll(optionsList);
	}

}

package org.dt340a.group6.sprint1.query;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;


public class DropTables {
	
	public static void ifTablesExist(){
		PersistenceUtil.dropTablesIfExist();
	}
	
	public static void main(String args[]) {
		DropTables.ifTablesExist();
	}

}

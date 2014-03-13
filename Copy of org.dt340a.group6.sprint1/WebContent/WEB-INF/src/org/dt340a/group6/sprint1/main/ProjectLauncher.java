package org.dt340a.group6.sprint1.main;

import org.dt340a.group6.sprint1.fileImport.CallFailureReader;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;

public class ProjectLauncher {
	


	public ProjectLauncher() {
		super();
	}

	public static void main(String[] args) {
		Stopwatch sw = new Stopwatch();
		PersistenceUtil.persistAll("/home/dr206/temp/qwertyzxcv.xls");
		System.out.println("all persisted");
		System.out.println("Time="+sw.elapsedTime());
	}

}

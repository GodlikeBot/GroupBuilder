package com.kn.groupBuilder.UnusedFiles;

import java.io.File;

public class GroupFolderCleaner {
	public boolean cleanGroupFolder(String defaultPath) {
		{
			File path = new File(defaultPath + "\\GroupsXML\\");
			try {
				for (File file : path.listFiles()) {
					if (file.isDirectory())
						cleanGroupFolder(defaultPath);
					file.delete();
				}
				path.delete();
			} catch (java.lang.NullPointerException e) {
			}
			return true;
		}
	}
}
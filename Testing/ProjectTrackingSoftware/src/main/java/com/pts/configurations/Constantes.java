package com.pts.configurations;

public class Constantes {

	private static String separador = java.nio.file.FileSystems.getDefault().getSeparator();
	private static String workDir = System.getProperty("user.dir");
	
	//public static String upload_folder = ".//src//main//resources//files//";
	public static String upload_folder = workDir+separador+"src"+ separador+"main"+separador+"resources"+separador+"files"+separador;
		
}

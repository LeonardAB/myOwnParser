/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edictreader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Dipta Mahardhika
 */
public class CreateSQL_edict {
    public String header = "-- Table created with edictReader \r\n" + 
			"-- https://github.com/diptakobu\r\n" + 
			"\r\n" + 
			"-- Table structure for table `edict`\r\n" + 
			"--\r\n" + 
			"\r\n" + 
			"CREATE TABLE IF NOT EXISTS `edict` (\r\n" + 
			"	`_id` varchar(255) PRIMARY KEY NOT NULL,\r\n" +  
			"	`kanji` varchar(255) NOT NULL,\r\n" + 
			"	`reading` varchar(255) NOT NULL,\r\n" + 
			"	`meaning` TEXT NOT NULL,\r\n" + 
			"	`common` int(11) NOT NULL\r\n" + 
                        ") ;\r\n" + 
			"\r\n" + 
			"--\r\n" + 
			"-- Data for table `edict`\r\n" + 
			"--\r\n" + 
			"\r\n" + 
			"INSERT INTO `edict` (`_id`, `kanji`, `reading`, `meaning`, `common`) VALUES";
	private static String content;

	public String getHeader(){
		return header;
	}
	
	public void setContent(String c){
		content = c;
	}
	
	public static void stringToFile(String name) {
		FileOutputStream fop = null;
		File file;

		try {

			file = new File(name);
			fop = new FileOutputStream(file);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done writing");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

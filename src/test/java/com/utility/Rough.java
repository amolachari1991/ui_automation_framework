package com.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Rough {

	public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date();
        String fileNameDate = formatter.format(date);
        String fileName = "Report_" + fileNameDate + ".txt";
        System.out.println("Generated filename: " + fileName);
	}
}

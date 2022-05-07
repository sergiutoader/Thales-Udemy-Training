package com.semanticsquare.thrillio.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class IOUtil {

	public static void read(String[] data, String filename) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			String line;
			int count = 0;
			while ((line = br.readLine()) != null) {
				data[count++] = line;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String read(InputStream inputStream) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
			String line;
			StringBuffer sb = new StringBuffer();
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static void write(String webpage, long id) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/sergiu/Programming/ANUL-4/Udemy/Thales-Udemy-Training/java/thrillio/pages/" + String.valueOf(id) + ".html"), "UTF-8"))) {
			bw.write(webpage);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

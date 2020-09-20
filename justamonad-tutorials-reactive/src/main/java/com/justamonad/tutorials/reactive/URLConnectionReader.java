package com.justamonad.tutorials.reactive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class URLConnectionReader {

	private URL url;

	public void read() {
		BufferedReader in = null;
		try {
			URLConnection yc = url.openConnection();
			in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String input;
			int count = 0;
			while ((input = in.readLine()) != null) {
				count = count + input.length();
			}
			System.out.println(Thread.currentThread().getName() + " :: " + Thread.currentThread().getId());
			System.out.println(url + " :: " + count);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new URLConnectionReader(new URL("https://www.google.com")).read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
package service;
// baekjoon

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder();
		String[] strings = null;
		// int input1 = 0;
		
		// input1 = Integer.parseInt(bufferedReader.readLine());
		strings = bufferedReader.readLine().split(" ");
		
		System.out.println(stringBuilder);
		bufferedReader.close();
	}
}
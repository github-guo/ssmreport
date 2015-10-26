package com.cargosmart.csmcs.report.entrance;

public class TestReg {
	public static void main(String[] args) {
		String ts="Big Schedules Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36iPhone";
		System.out.println(ts.matches("(^Apache-HttpClient.*)|(^Big Schedules(.*)iPhone$)"));
		
	}
}

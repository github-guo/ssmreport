package com.cargosmart.csmcs.report.entrance;

import java.util.Calendar;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Date d1=calendar.getTime();
		calendar.add(Calendar.MINUTE, 30);
		Date d2=calendar.getTime();
		System.out.println(d1.before(d2));
	}
}

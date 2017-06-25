package com.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
	
	private String name;
	
	private Timer timer;
	
	
	public MyTimerTask(String name, Timer timer) {
		
		this.name = name;
		
		this.timer = timer;
		
	}

	@Override
	public void run() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Calendar c = Calendar.getInstance();
		
		String dateStr = sdf.format(c.getTime());
		
		System.out.println(dateStr);
		
		timer.cancel();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}

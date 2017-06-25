package com.timer;

import java.util.Timer;

public class TimerTest {
	
	public void print() {
		
	new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				System.out.println(this);
			}
		}).start();
		
	
	
	}
	
	public static void main(String[] args) {
		
		Timer timer = new Timer();
		
		timer.schedule(new MyTimerTask("root", timer), 1000);
		
	}

}

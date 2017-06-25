package com.logback.statusListener;

import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;

/**
 * Added status listener of type [com.logback.statusListener.MyStatusListener]
 * 	About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
 * 	Naming appender as [STDOUT]
 * 	Assuming default type [ch.qos.logback.classic.encoder.PatternLayoutEncoder] for [encoder] property
 * 	Setting level of ROOT logger to DEBUG
 * 	Attaching appender named [STDOUT] to Logger[ROOT]
 * 	End of configuration.
 * 	Registering current configuration as safe fallback point
 * @author may
 *
 */
public class MyStatusListener implements StatusListener {

	@Override
	public void addStatusEvent(Status status) {
		
		System.out.println(status.getMessage());
		
		
	}
	

}

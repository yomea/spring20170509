package com.logback;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class HelloWorld2 {

  public static void main(String[] args) {

	  Logger logger = LoggerFactory.getLogger("chapters.introduction.HelloWorld2");
	    logger.debug("Hello world.");

	    // print internal state(打印内部状态)，这也可以在配置文件中配置，在根元素configuration上配置debug为true，默认为false
	    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	    StatusPrinter.print(lc);
  }
  
  /*
   * 
   * 16:50:18.045 [main] DEBUG chapters.introduction.HelloWorld2 - Hello world.
16:50:17,989 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.groovy]
16:50:17,990 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
16:50:17,990 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.xml]
16:50:17,994 |-INFO in ch.qos.logback.classic.BasicConfigurator@2e817b38 - Setting up default configuration.
   */
  
}
package com.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * 本程序直接取得 LoggerContext，创建新 JoranConfigurator 并设置它要操作的上下文，
 *	重置 logger 上下文，最后要求配置器用参数中的配置文件对上下文进行配置。同时打印了
 *	内部状态数据
 * @author may
 *
 */
public class JoranConfiguratorTest {

	private static final Logger logger = LoggerFactory.getLogger(JoranConfiguratorTest.class);

	public static void main(String[] args) {

		// assume SLF4J is bound to logback in the current environment
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {
			//logback内部默认也是通过这个类进行配置文件的加载的，如果要自己控制，可以像这样进行配置。
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc);
			// the context was probably already configured by default上文可能已经默认配置
			// configuration rules
			lc.reset();
			configurator.doConfigure("D:/spring20170509/logback_start_003/src/main/resources/logger.xml");
		} catch (JoranException je) {
			// StatusPrinter will handle this
		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(lc);//打印错误或者警告
		logger.info("Entering application.");

	}

}

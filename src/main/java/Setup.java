
// simple test code for jobmanager , without OSGi, JUnit,...
//  -- 1

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.simpl.PropertySettingJobFactory;
import org.wiperdog.jobmanager.JobClass;
import org.wiperdog.jobmanager.JobFacade;
import org.wiperdog.jobmanager.JobManagerException;
import org.wiperdog.jobmanager.JobResult;
import org.wiperdog.jobmanager.internal.JobFacadeImpl;

public class Setup {
	private SchedulerFactory sf;
	private Scheduler scheduler;

	public Scheduler setup() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		scheduler = sf.getScheduler();
		PropertySettingJobFactory jfactory = new PropertySettingJobFactory();
		jfactory.setWarnIfPropertyNotFound(false);
		scheduler.setJobFactory(jfactory);

		return scheduler;
	}
	
	public Scheduler setup(Properties prop) throws Exception {
		StdSchedulerFactory sf = new StdSchedulerFactory();
		sf.initialize(prop);
		scheduler = sf.getScheduler();
//		PropertySettingJobFactory jfactory = new PropertySettingJobFactory();
//		jfactory.setWarnIfPropertyNotFound(false);
//		scheduler.setJobFactory(jfactory);

		return scheduler;
	}
	
	public void shutdown() throws Exception {
		scheduler.shutdown();
		scheduler = null;
		sf = null;
	}

}

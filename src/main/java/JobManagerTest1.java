
// simple test code for jobmanager , without OSGi, JUnit,...
//  -- 1

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
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

public class JobManagerTest1 {


	public static void main(String [] args) {
		try {
			(new JobManagerTest1()).jobFacadeTest1();
		} catch (Exception e) {
		}
	}

	public JobManagerTest1() {
		
	}

	public void jobFacadeTest1() throws Exception {

		// we have to create Quartz scheduler in the outside of JobFacade.
		Scheduler sched = (new Setup()).setup();

		System.out.println("TestJobFacade2.jobFacadeTest1 staring");
		String jcname1 = "testjobclass";
		String jobname1 = "job1";
		String jobname2 = "job2";
		
		//
		sched.start();
		// create JobFacade
		JobFacade jf = new JobFacadeImpl(sched);

		JobClass jc = jf.createJobClass(jcname1, 1, 10000, 50000);
		JobDetail job1 = jf.createJob(jobname1, new String [] {"/bin/sleep", "1"}, false);
		JobDetail job2 = jf.createJob(jobname2, new String [] {"/bin/sleep", "1"}, false);

		jf.assignJobClass(jobname1, jcname1);
		jf.assignJobClass(jobname2, jcname1);
		
		Trigger t = null;
		List<JobResult> jr = null;
		
//		t = jf.createTrigger(jobname1, 0);
		t = jf.createTrigger(jobname1, "*/5 * * * * ? *");
		jf.scheduleJob(job1, t);
//		t = jf.createTrigger(jobname2, 1000);
		t = jf.createTrigger(jobname2, "*/5 * * * * ? *");
		jf.scheduleJob(job2, t);

		// 
		// reduce running time to avoid stopping build system.
		// TODO: increase sleep time on desktop test time.
		Thread.sleep(30000);
		jr = jf.getJobResult(jobname1);
		// check job result here.
		jr = jf.getJobResult(jobname2);
		// check job result here.
		System.out.println("shutting down" + new Date());
		sched.shutdown();
	}
}

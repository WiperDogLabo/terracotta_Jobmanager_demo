import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.wiperdog.jobmanager.internal.JobFacadeImpl;

public class JobManagerTest2 {
	private final int NUM_OF_JOB = 4;
	private final int INTERVAL = 4;
	
	public static void main(String[] args) {
		(new JobManagerTest2()).Terracotta_JobManager_Test2();
	}

	public JobManagerTest2() {

	}

	/**
	 * Test jobmanager Add jobs before trigger
	 */
	public void Terracotta_JobManager_Test2() {
		try {
			Scheduler sched = getScheduler();
			sched.start();

			JobFacadeImpl jf = new JobFacadeImpl(sched);
			List<ExecutableJob> listJob = new ArrayList<ExecutableJob>();
			for (int i = 1; i <= NUM_OF_JOB; i++) {
				if(!sched.checkExists(new JobKey("job" + i))){
					System.out.println("Add job " + i);
					ExecutableJob exej = new ExecutableJob();
					exej.setName("job" + String.valueOf(i));
					jf.createJob(exej);
					listJob.add(exej);	
				}
			}
			for (ExecutableJob exe : listJob) {
				System.out.println("Schedule " + exe.getName());
				Trigger trg = jf.createTrigger(exe.getName(), 0, INTERVAL * 1000);
				jf.scheduleJob(jf.getJob(exe.getName()), trg);
			}

			Thread.sleep(60000);
			sched.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Scheduler getScheduler() throws Exception {
		Properties prop = getProperties();
		Scheduler sched = new Setup().setup(prop);
		return sched;
	}

	private Properties getProperties() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("quartz.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}

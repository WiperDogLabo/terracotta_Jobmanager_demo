import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import org.quartz.JobDataMap;
import org.wiperdog.jobmanager.JobExecutable;


public class ExecutableJob implements JobExecutable, Serializable{
	String name;

	public Object execute(JobDataMap arg0) throws InterruptedException {
		HashMap ret = new HashMap();
		System.out.println(new Date().toGMTString() + " This is " + name + " running!");
		Thread.sleep(2000);
		ret.put("Job", name);
		return ret;
	}

	public String getArgumentString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public void stop(Thread arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void setName(String name){
		this.name = name;
	}

}

JobManager test
===============

These scripts tests org.wiperdog.jobmanager.  
(but not in OSGi env)  

1. prepare dependencies by using 'setup_dependency.sh'
2. Start terracotta server(using a dedicated terminal):  
        ./start_server.sh
The terracotta server configurations are written in the file ./tc-config.xml .
3. compmile source
         mvn install
4. execute tests:  
How to execute:    
         mvn exec:java -P <profile>
 profile: 
  - test1
	Test jobmanager with simple job
  - test2
	Test jobmanger with terracotta server with 4 simple jobs
	 + Config Quartz in quartz.properties.
	 + Open 2 terminal.
	 + Run mvn exec:java -P test2 on each terminal.
	 + Watch cluster system run and try to stop, restart each terminal to see how cluster works
	
		


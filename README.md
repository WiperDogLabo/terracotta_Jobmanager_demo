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
3. execute tests:  
How to execute:    
         mvn exec:java -P test1


@echo off
rem This file is there to ensure the java agent being set as vm-args when tomcat is started
echo Setting custom environment variables from %CATALINA_HOME%bin\setenv.bat

SET JRE_HOME="%CATALINA_HOME%\jre"
echo modified JRE_HOME to %JRE_HOME%

SET JAVA_OPTS=%JAVA_OPTS% -javaagent:"%CATALINA_HOME%\lib\org.springframework.instrument-3.0.2.RELEASE.jar"
echo modified JAVA_OPTS to %JAVA_OPTS%

echo Done setting custom environment variables

rem if "%OS%" == "Windows_NT" setlocal
rem ---------------------------------------------------------------------------
rem Start/Stop Script for the CATALINA Server
rem
rem Environment Variable Prequisites
rem
rem   CATALINA_HOME   May point at your Catalina "build" directory.
rem
rem   CATALINA_BASE   (Optional) Base directory for resolving dynamic portions
rem                   of a Catalina installation.  If not present, resolves to
rem                   the same directory that CATALINA_HOME points to.
rem
rem   CATALINA_OPTS   (Optional) Java runtime options used when the "start",
rem                   or "run" command is executed.
rem
rem   CATALINA_TMPDIR (Optional) Directory path location of temporary directory
rem                   the JVM should use (java.io.tmpdir).  Defaults to
rem                   %CATALINA_BASE%\temp.
rem
rem   JAVA_HOME       Must point at your Java Development Kit installation.
rem                   Required to run the with the "debug" argument.
rem
rem   JRE_HOME        Must point at your Java Runtime installation.
rem                   Defaults to JAVA_HOME if empty.
rem
rem   JAVA_OPTS       (Optional) Java runtime options used when the "start",
rem                   "stop", or "run" command is executed.
rem
rem   JPDA_TRANSPORT  (Optional) JPDA transport used when the "jpda start"
rem                   command is executed. The default is "dt_socket".
rem
rem   JPDA_ADDRESS    (Optional) Java runtime options used when the "jpda start"
rem                   command is executed. The default is 8000.
rem
rem   JPDA_SUSPEND    (Optional) Java runtime options used when the "jpda start"
rem                   command is executed. Specifies whether JVM should suspend
rem                   execution immediately after startup. Default is "n".
rem
rem   JPDA_OPTS       (Optional) Java runtime options used when the "jpda start"
rem                   command is executed. If used, JPDA_TRANSPORT, JPDA_ADDRESS,
rem                   and JPDA_SUSPEND are ignored. Thus, all required jpda
rem                   options MUST be specified. The default is:
rem
rem                   -agentlib:jdwp=transport=%JPDA_TRANSPORT%,
rem                       address=%JPDA_ADDRESS%,server=y,suspend=%JPDA_SUSPEND%
rem
rem   LOGGING_CONFIG  (Optional) Override Tomcat's logging config file
rem                   Example (all one line)
rem                   set LOGGING_CONFIG="-Djava.util.logging.config.file=%CATALINA_BASE%\conf\logging.properties"
rem
rem   LOGGING_MANAGER (Optional) Override Tomcat's logging manager 
rem                   Example (all one line)
rem                   set LOGGING_CONFIG="-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager"
rem
rem   TITLE           (Optional) Specify the title of Tomcat window. The default
rem                   TITLE is Tomcat if it's not specified.
rem                   Example (all one line)
rem                   set TITLE=Tomcat.Cluster#1.Server#1 [%DATE% %TIME%]
rem
rem ---------------------------------------------------------------------------

# Jenkins Seed

Playing around with [Job DSL Plugin](https://plugins.jenkins.io/job-dsl/)


## Troubleshoot

**ERROR: You must configure the DSL job to run as a specific user in order to use the Groovy sandbox.**
1. Check "Use Groovy Sandbox" in seed job configure
1. Install Authorize Project plugin 
1. Setup "Access Control for Builds" in "Configure Global Security"
1. (Project default Build Authorization -> Run as User who Triggered Build)
1. Execute Seed Job, Approve Signatures will be used by the seed job one by one
1. Then the seed job works well

[Source](https://issues.jenkins.io/browse/JENKINS-43509)

**ERROR: Scripts not permitted to use new groovy.util.ConfigSlurper.**  
**ERROR: Scripts not permitted to use method java.lang.Class getClassLoader**  
**ERROR: Scripts not permitted to use method groovy.util.ConfigSlurper setClassLoader groovy.lang.GroovyClassLoader**  
**ERROR: Scripts not permitted to use method groovy.util.ConfigSlurper parse java.lang.String**  
**ERROR: Scripts not permitted to use method groovy.lang.GroovyObject invokeMethod java.lang.String java.lang.Object**
**ERROR: Scripts not permitted to use staticMethod org.codehaus.groovy.runtime.DefaultGroovyMethods println groovy.lang.Closure java.lang.Object**
And also any Error like this:  
1. Navigate to jenkins > Manage jenkins > In-process Script Approval
1. There was a pending command, which I had to approve.

[Source](https://stackoverflow.com/questions/38276341/jenkins-ci-pipeline-scripts-not-permitted-to-use-method-groovy-lang-groovyobject)
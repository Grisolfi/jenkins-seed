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

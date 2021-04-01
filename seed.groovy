def slurper = new ConfigSlurper()
// fix classloader problem using ConfigSlurper in job dsl
slurper.classLoader = this.class.classLoader
def config = slurper.parse(readFileFromWorkspace('applications.dsl'))

config.applications.each {application, parameters ->
    println "Generating Job for $application"
    createJob(application, parameters)
}

def createJob(application, parameters){
    freeStyleJob(application){
        description(parameters.description)
        displayName("simple-job-$application")
        environmentVariables{
            env('APPLICATION_NAME', application)
            env('APPLICATION_VERSION', parameters.branch)
        }
        scm {
            git {
                remote {
                    name('CurrentRepo')
                    url(parameters.url)
                }
                extensions {
                    cleanAfterCheckout()
                    relativeTargetDirectory('repo1')
                }
            }
        }
        fortifyPlugin {
            analysisRunType {
                remoteAnalysisProjectType {
                    fortifyPython {
                        pythonRequirementsFile('requirements.txt')
                        pythonVersion('3')
                    }
                }
                uploadSSC {
                    appName(application)
                    appVersion(parameters.branch)
                }
            }
        }
    }
}   
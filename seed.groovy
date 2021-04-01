def slurper = new ConfigSlurper()
// fix classloader problem using ConfigSlurper in job dsl
slurper.classLoader = this.class.classLoader
def config = slurper.parse(readFileFromWorkspace('applications.dsl'))

config.applications.each {application, parameters ->
    println "Generating Job for $application"
    createJob(application, parameters)
}

def createJob(application, parameters){
    job(application){
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
        steps {
            shell('''echo "Hello from $application"''')
        }

    }
}   
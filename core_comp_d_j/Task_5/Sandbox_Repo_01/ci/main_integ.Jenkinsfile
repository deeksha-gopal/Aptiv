
@Library(value='corecomp-alsw-shared-library@master', changelog=false) _

import scm.Gerrit
def IS_GDSR = false
def IS_F360 = false
pipeline {
    agent any
        parameters {
            booleanParam(
                defaultValue: true,
                description: '''Build the main Integration pipeline of GDSR the branch jobs.
                              Does not have any impact in case of Change-Request.''',
                name: 'Build_GDSR'
            )
            booleanParam(
                defaultValue: true,
                description: '''Build the main Integration pipeline of F360 the branch jobs.
                              Does not have any impact in case of Change-Request.''',
                name: 'Build_F360'
            )
        }
    stages {
        stage('Prep') {
            steps {
                script {
                    sh "env | sort"
                    build wait: false, job: 'test_gdsr'
                    build wait: false, job: 'test_F360'
                    if (env.GERRIT_CHANGE_NUMBER) {
                        def mygerrit = new Gerrit(this, env)
                        mygerrit.get_list_of_changed_files()
                        IS_GDSR = mygerrit.check_changes_in_path(["src/GDSR"])
                        IS_F360 = mygerrit.check_changes_in_path(["src/F360"])
                        echo(String.valueOf(IS_GDSR))
                        echo(String.valueOf(IS_F360))
                    }
                    else {
                        IS_GDSR = params.Build_GDSR
                        IS_F360 = params.Build_F360
                    }
                }
            }
        }
        stage('Building Trackers') {
          parallel {
            stage("GDSR Gate Job") {
                when {
                    expression { IS_GDSR }
                }
                agent {
                    label 'Docker-Node-01'
                }
                steps {
                    script {
                        for(int i = 0;i<=20;i++) {
                            if (jenkins.model.Jenkins.instance.getItemByFullName("test_gdsr/${JOB_BASE_NAME}") != null) {
                                build(job: "test_gdsr/${JOB_BASE_NAME}")
                                break
                            }
                            sleep(time:30,unit:"SECONDS")
                            if (i == 20) {
                                echo "Maximum wait time exceed. Couldn't run 'test_gdsr/${JOB_BASE_NAME}'"
                                unstable(message: "Maximum wait time for branch indexing exceed. Couldn't run Gate job for GDSR")
                            }
                        }
                    }
                }
            }
            stage("F360 Gate Job") {
                when {
                    expression { IS_F360 }
                }
                agent {
                    label 'Docker-Node-02'
                }
                steps {
                    script {
                        for(int i = 0;i<=20;i++) {
                            if (jenkins.model.Jenkins.instance.getItemByFullName("test_F360/${JOB_BASE_NAME}") != null) {
                                build(job: "test_F360/${JOB_BASE_NAME}")
                                break
                            }
                            sleep(time:30,unit:"SECONDS")
                            if (i == 20) {
                                echo "Maximum wait time exceed. Couldn't run 'test_F360/${JOB_BASE_NAME}'"
                                System.exit(1)
                            }
                        }
                    }
                }
            }
          }
        }

    }
}

@Library(value='corecomp-alsw-shared-library@feature/GDSRCommonFunctions', changelog=false) _

import scm.Gerrit

import projects.corecomp.GDSRCommonFunctions

def CommonFunctions = new GDSRCommonFunctions(this, params)

pipeline {
    agent any

    stages {
        stage('test-steps') {
            steps {
                script {
                    CommonFunctions.test_steps()
                    echo "test steps"
                }
            }
            post {
                always {
                    script {
                        echo "test steps"
                        CommonFunctions.test_post()
                        CommonFunctions.sdd_coverage_post()
                    }
                }
        }

        }

    }

    post {
        cleanup {
            echo "Cleaning workspace..."
            deleteDir()
        }
    }
}

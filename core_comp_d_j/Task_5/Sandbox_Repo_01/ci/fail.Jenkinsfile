#!groovy

@Library(value='corecomp-alsw-shared-library@master', changelog=false) _
import docker.DockerImageTemplates

def docker_agent = new DockerImageTemplates(this)
def docker_agent_details = docker_agent.get_docker_details("generic", "5.0","alsw")


pipeline {
    agent {
        docker {
            alwaysPull true
            image docker_agent_details["imagename"]
            args docker_agent_details["args"]
            label docker_agent_details["label"]
        }
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        timeout(time: 10, unit: 'MINUTES')
        ansiColor('xterm')
        throttleJobProperty(
            categories: ['F360_ASTP'],
            limitOneJobWithMatchingParams: false,
            throttleEnabled: true,
            paramsToUseForLimit: '',
            throttleOption: 'category',
            maxConcurrentPerNode: 0,
            maxConcurrentTotal: 1
        )

    }
    stages {
        stage('fail') {
            steps {
                sh "exit 1"
            }
        }
    }
}

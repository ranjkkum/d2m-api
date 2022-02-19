pipeline {
  agent any
  environment {
    IMAGE = readMavenPom().getArtifactId()
    VERSION = readMavenPom().getVersion()
  }

  tools { 
    maven 'Maven' 
    jdk 'JDK11' 
  }
  triggers {
    githubPush()
  }
  stages{
    stage("Build App"){
      steps{
        sh 'mvn clean deploy'
      }
    }

    stage('Prepare Docker Context') {
      steps{
        sh """
          mkdir -p ${env.WORKSPACE}/docker
          cp ${env.WORKSPACE}/target/*.war ${env.WORKSPACE}/docker
          cp ${env.WORKSPACE}/Dockerfile ${env.WORKSPACE}/docker
          """
      }
    }

    stage('Build Docker Image') {
      steps{
        script {
          // sh 'printenv'
          dockerImage = docker.build(getDockerTag(), "--no-cache --build-arg APP_NAME=d2m-api-1 --build-arg BUILD_VERSION=0.0.2 -f ./docker/Dockerfile ./docker")
        }
      }
    }
    stage('Push Docker Image') {
      steps{
        script {
          // sh 'printenv'
          dockerImage = docker.push(getDockerTag())
        }
      }
    }
  }
}


def getDockerTag(){
  return "ranjkkum.jfrog.io/d2m-docker/${IMAGE}:prod-1.0.${env.BUILD_NUMBER}"
}

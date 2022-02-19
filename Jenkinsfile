pipeline {
  agent any
  environment {
    imagename = 'd2m-core-service'
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

    stage('Build Docker Image') {
      steps{
        script {
          sh 'printenv'
          dockerImage = docker.build "${imagename}:${env.BRANCH_NAME}-1.0.${env.BUILD_NUMBER}"
        }
      }
    }
  }
}


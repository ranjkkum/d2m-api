pipeline {
  agent any
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
          dockerImage = docker.build imagename
        }
      }
    }
  }
}


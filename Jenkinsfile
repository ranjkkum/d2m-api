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
    stage("build"){
      steps{
        sh 'mvn clean install'
      }
    }
  }
}


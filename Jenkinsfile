pipeline {
  agent any
  environment {
    APP = readMavenPom().getArtifactId()
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
          dockerImage = docker.build(getDockerTag(), "--no-cache --build-arg APP_NAME=${APP} --build-arg BUILD_VERSION=${VERSION} -f ./docker/Dockerfile ./docker")
        }
      }
    }
    stage('Push Docker Image') {
      steps{
        script {

          docker.withRegistry('https://ranjkkum.jfrog.io', 'jfrog'){
            dockerImage.push()
          }          
        }
      }
    }
  }
}


def getDockerTag(){
  return "ranjkkum.jfrog.io/d2m-docker/${APP}:prod-1.0.${env.BUILD_NUMBER}"
}

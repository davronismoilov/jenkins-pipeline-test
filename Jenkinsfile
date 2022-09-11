pipeline {
  environment {
    imagename = "jenkinstest"
    registryCredential = 'test'
    dockerImage = ''
  }
  tools{
    maven '3.8.5'
  }
  agent any
  stages {

    stage("test"){
    steps{
    sh 'mvn test'

     }
    }

    stage("package") {
      steps{
       sh 'mvn clean install'

       sh 'pwd'
      }
    }

    stage("docker run") {
      steps{
     sh 'docker-compose build'
     sh 'docker-compose up -d'

      }
    }
}
}

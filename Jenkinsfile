pipeline {
  environment {
    imagename = "jenkinstest"
    registryCredential = 'test'
    dockerImage = ''
  }
  tools{
    maven '3.8.6'
  }
  agent any
  stages {
//     stage('Cloning Git') {
//       steps {
//         git([url: 'https://github.com/theninjacoder-uz/TripProject-main.git', branch: 'master', credentialsId: 'test'])

//       }
//     }

    stage("package") {
      steps{
       sh 'mvn clean install'
      }
    }

    stage("docker run") {
      steps{
       sh 'docker compose up --build -d'
      }
    }
}
}

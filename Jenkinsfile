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
        
        sh 'curl -L "https://github.com/docker/compose/releases/download/1.28.5/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose'
         sh 'chmod +x /usr/local/bin/docker-compose'
        sh ' ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose'
        sh 'docker-compose --version'
        //sh 'docker --version'
//        sh 'docker compose up --build -d'
      }
    }
}
}

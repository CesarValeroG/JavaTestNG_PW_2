pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'saucedemo-creds', usernameVariable: 'SAUCE_USER', passwordVariable: 'SAUCE_PASS')]) {
                    bat 'mvn clean test'
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
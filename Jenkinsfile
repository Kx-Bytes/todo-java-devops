pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'jdk17'
    }

    environment {
        DOCKER_IMAGE = 'kixan123/todo-java-app'
    }

    stages {
        stage('Build and Test') {
            steps {
                sh './mvnw clean package -DskipTests=false'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-creds') {
                        def app = docker.build("${env.DOCKER_IMAGE}")
                        app.push('latest')
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build, test, and Docker push successful!'
        }
        failure {
            echo '❌ Pipeline failed.'
        }
    }
}

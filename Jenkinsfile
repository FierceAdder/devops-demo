pipeline {
    agent any

    environment {
        APP_NAME = 'devops-demo'
        JAR_NAME = 'devops-demo-1.0.0.jar'
        MVN     = '/usr/local/bin/mvn'
    }

    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo "Branch: ${env.GIT_BRANCH} | Commit: ${env.GIT_COMMIT}"
            }
        }

        stage('Test') {
            steps {
                sh '${MVN} test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build') {
            steps {
                sh '${MVN} clean package -DskipTests'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                echo "Build ${env.BUILD_NUMBER} archived successfully."
            }
        }
    }

    post {
        success { echo "Pipeline succeeded for build ${env.BUILD_NUMBER}" }
        failure { echo "Pipeline FAILED for build ${env.BUILD_NUMBER}" }
    }
}

pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk   'JDK11'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    // Publish TestNG HTML report
                    publishHTML(target: [
                        allowMissing         : true,
                        alwaysLinkToLastBuild: true,
                        keepAll              : true,
                        reportDir            : 'target/surefire-reports',
                        reportFiles          : 'index.html',
                        reportName           : 'TestNG Report'
                    ])
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                // Archive screenshots captured during the test run
                archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
                // Archive the Surefire XML results for downstream processing
                archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true
            }
        }
    }

    post {
        failure {
            echo "Pipeline failed — check TestNG Report and archived screenshots for details."
        }
        success {
            echo "All tests passed."
        }
    }
}

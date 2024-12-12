pipeline {
    agent any

    environment {
        GITHUB_TOKEN = credentials('f1524b17-d6f4-4a88-b967-1b4624752ebf')
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew build'
            }
        }
        stage('Code Coverage') {
            steps {
                sh './gradlew jacocoTestCoverageVerification'
            }
        }
    }
    post {
        success {
            script {
                def prStatus = 'success'
                updateGitHubStatus(prStatus)
            }
        }
        
        failure {
            script {
                def prStatus = 'failure'
                updateGitHubStatus(prStatus)
            }
        }
    }
}
def updateGitHubStatus(String status) {
    
    withCredentials([string(credentialsId: 'f1524b17-d6f4-4a88-b967-1b4624752ebf', variable: 'GITHUB_TOKEN')]) {
        def prUrl = "https://api.github.com/repos/pacots/PROF-Jenkins-Exercise-1/statuses/${env.GIT_COMMIT}"
        def data = """
        {
            "state": "${status}",
            "context": "Jenkins CI"
        }
        """
        sh "curl -X POST -H 'Authorization: token ${GITHUB_TOKEN}' -d '${data}' ${prUrl}"
    }
}
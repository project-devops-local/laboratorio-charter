pipeline {
    agent {label 'maven'}

    stages {
         stage('clone repositorio') {
            steps {
                script{
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/project-devops-local/pagina-web.git']])
                }
            }
        }

        stage('sonar'){
            steps{
                script{
                    container('sonar'){
                        withSonarQubeEnv('sonarqube') {
                            sh "sonar-scanner -Dsonar.projectKey=my-project-html -Dsonar.sources=index.html -Dsonar.language=html"
                        }

                         timeout(time: 1, unit: 'HOURS') {
                            // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                            // true = set pipeline to UNSTABLE, false = don't
                            waitForQualityGate abortPipeline: true
                        }
                    }
                }
            }
        }

        /*stage('ssh') {
            steps {
                script{
                    container('sshpass'){
                       sh 'sshpass -p "1234" ssh -o StrictHostKeyChecking=no devops@10.20.2.15 "mkdir -p /usr/share/nginx/html/home/demo/"'
                       sh 'sshpass -p "1234" scp -o StrictHostKeyChecking=no index.html devops@10.20.2.15:/home/devops/'
                       sh 'sshpass -p "1234" ssh -o StrictHostKeyChecking=no devops@10.20.2.15 "mv /home/devops/index.html  /usr/share/nginx/html/home/demo/"'
                       sh 'sshpass -p "1234" ssh -o StrictHostKeyChecking=no devops@10.20.2.15 "ls /usr/share/nginx/html/home/demo/"'
                    
                        withCredentials([sshUserPrivateKey(credentialsId: 'server-key', keyFileVariable: 'KEY_FILE')]) {
                            sh 'ssh -i $KEY_FILE -o StrictHostKeyChecking=no devops@10.20.2.15 "mkdir -p /usr/share/nginx/html/home/demo/"'
                            sh 'scp -i $KEY_FILE -o StrictHostKeyChecking=no index.html devops@10.20.2.15:/home/devops/'
                            sh 'ssh -i $KEY_FILE -o StrictHostKeyChecking=no devops@10.20.2.15 "mv /home/devops/index.html  /usr/share/nginx/html/home/demo/"'
                            sh 'ssh -i $KEY_FILE -o StrictHostKeyChecking=no devops@10.20.2.15 "ls /usr/share/nginx/html/home/demo/"'
                        }
                        
                    }
                }
            }
        }*/
    }
}

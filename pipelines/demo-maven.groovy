pipeline {
    agent {label 'maven'}

    stages {
        stage('git clone') {
            steps {
               script{
                 checkout scmGit(branches: [[name: '*/sonar']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/darinpope/java-web-app.git']])
               }
            }
        }
        stage('CI') {
            steps {
               script{
                   container('maven'){
                     withSonarQubeEnv('sonarqube') {
                        sh "mvn clean package "
                        sh "mvn clean verify sonar:sonar" 
                        sh "find ." 
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
  
        stage("CD"){
            
            steps{
                script{
                    container('maven'){
                        dir('target'){       
                             sh "nohup java -jar demo-0.0.1-SNAPSHOT.jar > salida.log 2>&1 &"
                             sh "ls -ltra"
                             sleep "60"
                             sh "cat salida.log"
                             sh "echo 'se acabo el despliegue gracias por visualizar'"
                        }
                       
                    }
                }
            }
        }
    }
}

env.REMOTE_URL = 'http://172.19.0.2:4444/wd/hub'

pipeline {
    agent any

    tools {
        maven 'mvn'
    }

    stages {
        stage("SCM") {
            steps {
                git credentialsId: 'git_ssh', url: 'git@github.com:shchurkobohdan/demo.git'
            }
        }

        stage("Tests") {
            steps {
                sh "mvn clean test -DremoteUrl=$REMOTE_URL -Premote"
            }
        }

        stage("Allure report") {
            steps {
                allure([
                    includeProperties: true,
                    jdk: '',
                    properties: [[key: 'allure.results.directory', value: 'target/allure-results']],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
}
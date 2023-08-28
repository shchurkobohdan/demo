pipeline {
    agent any

    stages {
        stage("SCM") {
            step {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
            }
        }

        stage("Tests") {
            step {
                sh "mvn -Dmaven.test.failure.ignore=true clean test site"
            }
        }

        stage("Allure report") {
            script {
                step {
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
}
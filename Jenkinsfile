pipeline {
    agent any

    tools {
        maven "3.9.4"
    }

    stages {
        stage("SCM") {
            steps {
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'
            }
        }

        stage("Tests") {
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true clean test site"
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
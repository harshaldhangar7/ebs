pipeline {
    agent any

    stages {
        stage('scm checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/harshaldhangar7/ebs.git'
            }
        }
        stage('build ') {
            steps {
                withMaven(globalMavenSettingsConfig: '', jdk: 'JAVA_HOME', maven: 'MVN_HOME', mavenSettingsConfig: '', traceability: true) {
    sh 'mvn validate'
}
            }
        }
               stage('package ') {
            steps {
                withMaven(globalMavenSettingsConfig: '', jdk: 'JAVA_HOME', maven: 'MVN_HOME', mavenSettingsConfig: '', traceability: true) {
    sh 'mvn package'
}
            }
        }
    
    }
}

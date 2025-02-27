pipeline {
    agent any

    environment {
        TOMCAT_PATH = '/var/lib/tomcat9'
        WAR_FILE = 'taxiapp*.war'
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
        PATH = "${JAVA_HOME}/bin:${PATH}"
        TEST_SCRIPT = 'selenium_test_booking.py'
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sh '''
                sudo -S systemctl stop tomcat9
                sudo -S rm -rf $TOMCAT_PATH/webapps/taxiapp*
                sudo -S cp target/$WAR_FILE $TOMCAT_PATH/webapps/taxiapp1.war
                sudo -S systemctl start tomcat9
                '''
            }
        }

        stage('Run Tests') {
            steps {
                sh 'python3 $TEST_SCRIPT'
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Build or deployment failed! Check logs.'
        }
    }
} 

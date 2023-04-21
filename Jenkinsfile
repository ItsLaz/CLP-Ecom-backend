pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'itslaz/ecom-b'
    }

    stages {
        stage('Building and create .jar file'){
            steps {
                echo 'Building the .jar file'

                //Builds and create our .jar file
                sh 'mvn clean package'
            }
        }

        stage('Creating docker image'){
            steps{
                //builds docker image
                sh 'sudo docker build -t ${DOCKER_IMAGE}:latest .'
            }
        }

        stage('Deploying into docker container'){
            steps{
                //stop any running containers of this image
                sh 'sudo docker rm -f $(sudo docker ps -af name=ecommerce-backend -q)'

                //run latest version of image in container
                sh 'sudo docker run -p 4798:4798 --name ecommerce-backend ${DOCKER_IMAGE}:latest'

            }
        }
    }
}
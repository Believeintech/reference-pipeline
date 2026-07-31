def call(String dockerHubUser, String serviceName, String buildTag, String credentialsId) {
    docker.withRegistry('https://index.docker.io/v1/', credentialsId) {
        sh "docker tag ${serviceName}:${buildTag} ${dockerHubUser}/${serviceName}:${buildTag}"
        sh "docker push ${dockerHubUser}/${serviceName}:${buildTag}"
    }
}
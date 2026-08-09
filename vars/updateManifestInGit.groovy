def call(String dockerHubUser, String serviceName, String buildTag, String manifestPath, String gitCredentialsId) {
    withCredentials([usernamePassword(credentialsId: gitCredentialsId, usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]) {
        sh """
          sed -i "s|image:.*|image: ${dockerHubUser}/${serviceName}:${buildTag}|" ${manifestPath}
          git config user.email "jenkins@ci.local"
          git config user.name "Jenkins CI"
          git add ${manifestPath}
          git commit -m "Update ${serviceName} image to ${buildTag} [skip ci]"
          git push https://\$GIT_USER:\$GIT_TOKEN@github.com/Believeintech/microservices-demo.git HEAD:main
        """
    }
}
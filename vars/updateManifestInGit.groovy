def call(String dockerHubUser, String serviceName, String buildTag, String manifestPath, String gitCredentialsId) {
    withCredentials([usernamePassword(credentialsId: gitCredentialsId, usernameVariable: 'GIT_USER', passwordVariable: 'GIT_TOKEN')]) {
        sh """
          rm -rf gitops-clone-${serviceName}-${buildTag}
          git clone https://\$GIT_USER:\$GIT_TOKEN@github.com/Believeintech/GitOps-Continuous-Deployment-Platform.git gitops-clone-${serviceName}-${buildTag}
          cd gitops-clone-${serviceName}-${buildTag}
          sed -i "s|image:.*|image: ${dockerHubUser}/${serviceName}:${buildTag}|" ${manifestPath}
          git config user.email "jenkins@ci.local"
          git config user.name "Jenkins CI"
          git add ${manifestPath}
          git commit -m "Update ${serviceName} image to ${buildTag} [skip ci]"
          git push origin main
          cd ..
          rm -rf gitops-clone-${serviceName}-${buildTag}
        """
    }
}
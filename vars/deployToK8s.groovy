def call(String dockerHubUser, String serviceName, String buildTag, String kubeCredentialsId, String manifestPath) {
    withKubeConfig(credentialsId: kubeCredentialsId) {
        sh """
          sed -i "s|image: ${serviceName}|image: ${dockerHubUser}/${serviceName}:${buildTag}|" ${manifestPath}
          kubectl apply -f ${manifestPath}
          kubectl rollout status deployment/${serviceName} --timeout=60s
        """
    }
}
def call(String serviceName, String buildTag, String severity = 'CRITICAL') {
    sh "trivy image --severity ${severity} --exit-code 1 ${serviceName}:${buildTag}"
}
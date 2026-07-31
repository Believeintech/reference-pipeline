def call(String serviceName, String buildTag) {
    sh "docker build -t ${serviceName}:${buildTag} src/${serviceName}"
}
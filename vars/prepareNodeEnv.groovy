def call(String serviceName) {
    sh """
      docker run --rm \
        -v jenkins_home:/var/jenkins_home \
        -w \${WORKSPACE}/src/${serviceName} \
        node:20-alpine \
        sh -c "apk add --update --no-cache python3 make g++ && node -v && npm -v && if [ -f package-lock.json ]; then npm ci --no-audit --no-fund; else npm install --legacy-peer-deps --no-audit --no-fund; fi"
    """
}
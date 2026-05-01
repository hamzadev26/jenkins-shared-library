def call(imageName, imageTag) {
    echo "Image build ho rahe hain..."
    echo "Building Image: ${imageName}:${imageTag}"

    sh """
        set -e
        docker build -t ${imageName}:${imageTag} .
    """
}

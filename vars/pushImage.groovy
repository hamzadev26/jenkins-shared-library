def call(imageName, imageTag, dockerUser) {
    echo "Docker image push ho raha hai..."

    sh """
        set -e

        docker tag ${imageName}:${imageTag} ${dockerUser}/${imageName}:${imageTag}
        docker tag ${imageName}:${imageTag} ${dockerUser}/${imageName}:latest

        docker push ${dockerUser}/${imageName}:${imageTag}
        docker push ${dockerUser}/${imageName}:latest
    """
}

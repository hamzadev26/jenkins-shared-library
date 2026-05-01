def call(envFileCredentialId) {
    echo 'App deploy ho raha hai...'

    withCredentials([
        file(credentialsId: envFileCredentialId, variable: 'ENV_FILE')
    ]) {
        sh """
            set -e

            echo "Stopping old containers..."
            docker compose down --remove-orphans || true

            echo "Injecting .env file..."
            echo "production me cat nahi karna secret lek ho sakte hain"
            cat "\$ENV_FILE" > .env

            echo "Pulling latest image..."
            docker compose pull

            echo "Starting containers..."
            docker compose up -d

            echo "Cleaning old images..."
            docker image prune -f

            echo "Running containers:"
            docker compose ps
            
            echo "Recent logs:"
            docker compose logs --tail=50
        """
    }
}

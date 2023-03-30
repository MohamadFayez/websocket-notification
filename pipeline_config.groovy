libraries {
    common {
        component = "websocket-notification" // Microservice name
    }
    email {
        user_email = "mohamed.fayez@mdi.com.eg" // users emails to send the build result to
        cc = "" // users emails to cc
    }
    //TODO change docker library to podman for IMD cluster migration
    docker {
		// The path in Jfrog artifactory to store the docker image
		docker_repo = "microservices/websocket-notification" //i.e: microservices/communication-manager-devopstest
	}
    helm {
        service_port = 7084 //The port used by OpenShift service
        service_targetport = 7084 //The port exposed from the service container
        // Below values are used by OpenShift to check the microservice health.
        liveness_path = "/mgmt/health" //Path on which the service liveness is checked by OpenShift
        readiness_path = "/mgmt/health" //Path on which the service readiness is checked by OpenShift
        probes_initialDelaySeconds = 150 //Number of seconds after the container has started before liveness or readiness probes are initiated. Defaults to 0 seconds. Minimum value is 0.
        probes_timeoutSeconds = 1 //Number of seconds after which the probe times out. Defaults to 1 second. Minimum value is 1.
        probes_periodSeconds = 10 //How often (in seconds) to perform the probe. Default to 10 seconds. Minimum value is 1.
        probes_successThreshold = 1 //Minimum consecutive successes for the probe to be considered successful after having failed. Defaults to 1. Must be 1 for liveness and startup Probes. Minimum value is 1.
        probes_failureThreshold = 3 //When a probe fails, Kubernetes will try failureThreshold times before giving up. Giving up in case of liveness probe means restarting the container. In case of readiness probe the Pod will be marked Unready. Defaults to 3. Minimum value is 1.
    }
}

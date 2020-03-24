
# declare-transit-movement-unloading-journey-test

## Installing and running the tests in Docker

   ### Install Docker
   * Linux: https://docs.docker.com/install/linux/docker-ce/ubuntu/ followed by
            https://docs.docker.com/install/linux/linux-postinstall/
   * Mac (install docker deskop): https://docs.docker.com/docker-for-mac/install/

   ### Start service manager

    sm -start CTC_TRADERS_ARRIVAL -r

   ### Change to the docker/chrome directory

    cd $WORKSPACE/declare-transit-movement-unloading-journey-test/docker/chrome/

   ### Build the chrome instance (including the . at the end)
   *You only need to do this once:*

     docker build -t chrome .

   ### Start the Docker container

   *This will map service manager, chrome and vnc viewer ports to a Docker alias on the container instance.*

   `./rundocker.sh` or

   `./rundocker.sh smenv`

   **Note - Passing the "smenv" argument is required if you are running a service manager virtualenv.**

   ### Run the journey tests

    cd $WORKSPACE/declare-transit-movements-unloading-journey-test
    ./run_remote.sh (runs in remote container using headless browser) or
    ./run_jenkins.sh (runs in remote container using GUI browser)

   ### Connect to the VNC server (View running tests)

   Connect to the image on `vnc://localhost:5900` with your favorite vnc client (if you're on a mac just use Safari)

   When prompted for a password, enter: `secret`

   ### Updating selenium version

   Edit  `~/docker/chrome/Dockerfile`

   Update `FROM selenium/standalone-chrome-debug:<version>` to reflect [Selenium version](https://github.com/SeleniumHQ/docker-selenium/releases)


   ### Useful docker commands

    docker ps                                   # Lists all running containers
    docker stop $(docker ps -a -q)              # Stops all running containers
    docker exec -it <container id> /bin/bash    # Bash access to container

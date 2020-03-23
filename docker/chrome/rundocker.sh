#!/usr/bin/env bash

SM_TYPE=$1

if [ "$SM_TYPE" = "smenv" ]; then
    SM_OUTPUT=$(source ~/servicemanager/bin/activate && sm -s | egrep 'PASS|BOOT' | awk '{ print $12 }')
else
    SM_OUTPUT=$(sm -s | egrep 'PASS|BOOT' | awk '{ print $12 }')
fi

for PORT in $SM_OUTPUT; do
   MAPPED_PORTS="$MAPPED_PORTS$PORT->$PORT,"
done

MAPPED_PORTS="${MAPPED_PORTS}11000->11000"

echo MAPPED_PORTS: $MAPPED_PORTS

if [ "$(uname)" == "Darwin" ]; then
    echo "Mac Docker image"
    docker run --rm -d --name chrome -p 4444:4444 -p 5900:5900 -e PORT_MAPPINGS=$MAPPED_PORTS -e TARGET_IP='host.docker.internal' chrome
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    echo "Linux Docker image"
    docker run --network host --rm -d chrome -p 4444:4444 -p 5900:5900 -e PORT_MAPPINGS=$MAPPED_PORTS -e TARGET_IP='host.docker.internal' chrome
fi



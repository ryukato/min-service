#!/bin/sh
# ----------------------------------------------------------------------------
echo "Make sure you have default virtual machine"
eval "$(docker-machine env default)"
./mvnw clean package docker:build -Dmaven.test.skip=true
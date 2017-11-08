#!/bin/sh
# ----------------------------------------------------------------------------
eval "$(docker-machine env default)"
./mvnw clean package docker:build -Dmaven.test.skip=true
#!/bin/bash
# start a standalone locator
locatorPort=$1
jmxPort=$2
pulsePort=$3
gfsh start locator --name=locator_${locatorPort} --dir=/var/tmp --port=${locatorPort} --http-service-port=${pulsePort}  --force \
--J=-Dgemfire.jmx.manager=true --J=-Dgemfire.jmx.manager-port=${jmxPort}
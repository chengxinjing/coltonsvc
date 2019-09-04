#!/bin/bash
# start a standalone locator
locatorPort=$1
jmxPort=$2
pulsePort=$3
host=`tail -n 1 /ect/hosts | awk '{print $1}'`
gfsh start locator --name=locator_${locatorPort} --dir=/gemfire/log/ --port=${locatorPort} --http-service-port=${pulsePort}  --force \
--J=-Dgemfire.jmx-manager=true --J=-Dgemfire.jmx-manager-start=true --J=-Dgemfire.jmx-manager-port=${jmxPort} --J=-Dgemfire.jmx-manager-bind-address=${host}
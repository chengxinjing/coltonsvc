#!/bin/bash
locatorPort=31431
jmxPort=1099
pulsePort=8092
cd /gemfire
#delete previous locator
rm -rf /var/tmp/locator_${locatorPort}
#start locator
./bin/startLocator.sh ${locatorPort} ${jmxPort} ${pulsePort}
#start server
jarVersion=`ls | grep 'aws_gemfire'`
host=`tail -n 1 /ect/hosts | awk '{print $1}'`
javaOptions="-Xms1024m -Xmx1024m"
timestamp=`date +'%Y%m%d%H%M%S'`
java ${javaOptions} -Dgeode.server.hostname=${host} -Dgemfire.name=${SERVER_NAME} -Dgemfire.locators=${LOCATORS} -Dgeode.server.disk.location=/var/tmp/disk -jar  ${jarVersion} > /gemfire/log/gemfire.${timestamp}.log
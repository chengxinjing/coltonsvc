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
javaOptions="-Xms1024m -Xmx1024m"
java ${javaOptions}  -Dgemfire.name=${SERVER_NAME} -Dgemfire.locators=${LOCATORS} -Dgeode.server.disk.location=/var/tmp/disk -jar  ${jarVersion} >> /var/tmp/gemfire.log
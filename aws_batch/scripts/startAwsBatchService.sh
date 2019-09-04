#!/bin/bash
cd /batch
jar=`ls | grep 'aws_batch'`
timestamp=`date +'%Y%m%d%H%M%S'`
javaOption="-Xms1024m -Xmx1024m -Dgeode.locator.hostname=${LOCATOR_HOST} -Dgeode.locator.port=${LOCATOR_PORT} -Dsource.download.url=${DOWNLOAD_URL}"

java ${javaOption} -jar ${jar} > /batch/log/batch.${timestamp}.log
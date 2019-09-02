#!/bin/bash
cd /batch
jar=`ls | grep 'aws_batch'`

javaOption="-Xms1024m -Xmx1024m -Dgeode.locator.hostname=${LOCATOR_HOST} -Dgeode.locator.port=${LOCATOR_PORT}"

java ${javaOption} -jar ${jar} >> /var/tmp/batch.log
#!/bin/bash

credentialPath=/var/tmp/credentials.properties
JAVAOption="-Xms1024m -Xmx1024m -Daws.credential=${credentialPath}"
jar=`ls /web | grep 'aws_web'`
java ${JAVAOption} -jar /app/${jar} >> /var/tmp/aws_web.log
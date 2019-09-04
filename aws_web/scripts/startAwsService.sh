#!/bin/bash
timestamp=`date +'%Y%m%d%H%M%S'`
credentialPath=/web/config/credentials.properties
JAVAOption="-Xms1024m -Xmx1024m -Daws.credential=${credentialPath}"
jar=`ls /web | grep 'aws_web'`
java ${JAVAOption} -jar /app/${jar} > /web/log/aws_web.${timestamp}.log
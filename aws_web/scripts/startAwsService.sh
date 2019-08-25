#!/bin/bash

credentialPath=/var/tmp/credentials.properties
JAVAOption="-Xms1024m -Xmx1024m -Daws.credential=${credentialPath}"
java ${JAVAOption} -jar /app/aws_web-1.0.jar >> /var/tmp/aws_web.log
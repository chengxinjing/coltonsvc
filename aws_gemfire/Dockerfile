FROM docker.io/openjdk
WORKDIR /gemfire
COPY ./target/aws_gemfire*.jar ./
COPY ./scripts/startAwsGemfireService.sh ./
RUN exec chmod +x ./startAwsGemfireService.sh
ENV LOCATORS locators
ENV PULSE_PORT 8092
EXPOSE ${PULSE_PORT}
ENTRYPOINT["startAwsGemfireService.sh"]
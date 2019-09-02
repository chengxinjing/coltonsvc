FROM docker.io/openjdk
WORKDIR /batch
COPY ./target/aws_batch*.jar ./
COPY ./scripts/*.sh ./
RUN mkdir ./bin && mv *.sh ./bin && \
chmod +x ./bin/*.sh
ENV LOCATOR_HOST host
ENV LOCATOR_PORT port
EXPOSE 9092
ENTRYPOINT ["/batch/bin/startAwsBatchService.sh"]
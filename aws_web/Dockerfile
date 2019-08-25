FROM docker.io/openjdk
WORKDIR /app
COPY ./target/aws_web-*.jar ./
COPY ./scripts/startAwsService.sh ./
RUN exec chmod +x ./startAwsService.sh
ENV AWS_ACCESS_KEY_ID active
ENV AWS_SECRET_KEY active
EXPOSE 8080

ENTRYPOINT ["/app/startAwsService.sh"]
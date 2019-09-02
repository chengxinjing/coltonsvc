FROM docker.io/openjdk
WORKDIR /web
COPY ./target/aws_web-*.jar ./
COPY ./scripts/startAwsService.sh ./
RUN exec chmod +x ./startAwsService.sh
ENV AWS_ACCESS_KEY_ID accessKey
ENV AWS_SECRET_KEY secretKey
EXPOSE 8080
ENTRYPOINT ["/web/startAwsService.sh"]
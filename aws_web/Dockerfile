FROM docker.io/openjdk
WORKDIR /web
COPY ./target/aws_web-*.jar ./
COPY ./scripts/startAwsService.sh ./
RUN exec chmod +x ./startAwsService.sh
ENV VOLUME_DIR /var/tmp
VOLUME ${VOLUME_DIR} /var/tmp
ENV AWS_ACCESS_KEY_ID accessKey
ENV AWS_SECRET_KEY secretKey
EXPOSE 8080

ENTRYPOINT ["/app/startAwsService.sh"]
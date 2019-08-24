FROM docker.io/openjdk
WORKDIR /app
COPY ./target/aws_web-1.0.jar ./
ENV AWS_ACCESS_KEY_ID active
ENV AWS_SECRET_KEY active
EXPOSE 8080
ENTRYPOINT ["java","-Xms500 -Xmx1024 -jar /app/aws_web-1.0.jar"]
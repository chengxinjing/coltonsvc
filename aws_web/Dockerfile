FROM docker.io/openjdk
WORKDIR /app
COPY ./target/aws_web-1.0.jar ./
ENV AWS_ACCESS_KEY_ID AKIA2BNIF6PHNXYCY6XB
ENV AWS_SECRET_KEY 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
EXPOSE 8080
ENTRYPOINT ["java","-Xms500 -Xmx1024 -jar /app/aws_web-1.0.jar"]
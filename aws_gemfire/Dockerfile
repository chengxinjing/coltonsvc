FROM docker.io/openjdk
WORKDIR /gemfire
COPY ./target/aws_gemfire*.jar ./
COPY ./scripts/* ./
RUN  chmod +x ./*.sh && \
mkdir bin && \
mv *.sh ./bin; \
# install the requirement software
yum install wget which -y; \
wget https://www-us.apache.org/dist/geode/1.9.0/apache-geode-1.9.0.tgz && \
tar -zxvf apache-geode-1.9.0.tgz && \
rm -f apache-geode*.tgz && \
ln -sf /gemfire/apache-geode-1.9.0/bin/gfsh /usr/bin/gfsh

ENV LOCATORS locators
ENV SERVER_NAME MAIN
EXPOSE 31431 31435 8092 1099
#ENV PULSE_PORT 8092
#EXPOSE ${PULSE_PORT}
ENTRYPOINT ["/gemfire/bin/startAwsGemfireService.sh"]
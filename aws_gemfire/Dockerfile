FROM docker.io/openjdk
WORKDIR /gemfire
COPY ./target/aws_gemfire*.jar ./
COPY ./scripts/* ./
RUN  chmod +x ./*.sh && \
mkdir bin && \
mv *.sh ./bin; \
# install the requirement software
yum install wget which -y; \
wget http://mirror.bit.edu.cn/apache/geode/1.9.0/apache-geode-1.9.0.tgz && \
tar -zxvf apache-geode-1.9.0 && \
rm -f apache-geode*.tgz && \
ln -sf /gemfire/apache-geode-1.9.0/bin/gfsh /usr/bin/gfsh

ENV LOCATORS locators
#ENV PULSE_PORT 8092
#EXPOSE ${PULSE_PORT}
ENTRYPOINT ["/gemfire/bin/startAwsGemfireService.sh"]
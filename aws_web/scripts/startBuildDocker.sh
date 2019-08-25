currentDir=`pwd`
# get the source code
git clone https://github.com/chengxinjing/coltonsvc.git
cd ${currentDir}/coltonsvc/aws_web
#package
mvn clean package -Dmaven.test.skip=true
#build docker image
docker build -t aws_web:v1.0.1 .>${currentDir}/build.log
imageId=`grep 'Successfully built' ${currentDir}/build.log | cut -f3 -d' '`
docker run -p 8080:8080 -v /var/tmp:/var/tmp --privileged --name=aws_web_${imageId} -d ${imageId}
rm -f ${currentDir}/build.log
exit $?
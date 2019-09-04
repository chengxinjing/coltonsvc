#!/bin/bash
#install requirement software
isInstall(){
  type $1
  if [ `echo $?` != 0 ]; then
    case $1 in
    git)
      sudo  yum install git-all.noarch -y
      ;;
    docker)
      sudo yum install docker.x86_64 -y
       ;;
     mvn)
      sudo yum install maven.noarch -y
      ;;
    esac

  fi
}


sudo yum install git-all.noarch -y
sudo yum install docker.x86_64 -y
sudo yum install maven.noarch -y
sudo systemctl enable docker
if [ `echo $?` != 0 ]; then
 sudo yum install docker.x86_64 -y
 sudo systemctl enable docker
fi
sudo gpasswd -a ${USER} docker
if [ `echo $?` != 0 ]; then
   sudo groupadd docker
   sudo gpasswd -a ${USER} docker

fi
sudo systemctl restart docker

git clone https://github.com/chengxinjing/coltonsvc.git
#build
cd ./coltonsvc
mvn clean package -Dmaven.test.skip=true

#build the aws_web
if [ $# -eq 0 ]; then
    version=1
fi
version=$1
cd ./aws_web
docker build -t aws_web:v1.0.${version} .

#build the aws_gemfire
cd ../aws_gemfire
docker build -t aws_gemfire:v1.0.${version} .

#build the aws_batch

cd ../aws_batch
docker build -t aws_batch:v1.0.${version} . 

#run the container
cd ~
absoulteDir=`pwd`
mkdir -p batch/log
mkdir -p gemfire/log
mkdir -p web/log && mkdir web/config

hostname=`hostname -I | awk '{print $1}'`
url="http://${hostname}:8080/aws/download/"

docker run -d  -v $absoulteDir/gemfire/log:/gemfire/log --name gemfire_${version} -p 31431:31431 -p 8092:8092  -P -e LOCATORS=localhost[31431] aws_gemfire:v1.0.${version}

docker run -d -v $absoulteDir/batch/log:/batch/log --name batch_${version} -p 9092:9092 -e LOCATOR_HOST=gemfire -e LOCATOR_PORT=31431 -e DOWNLOAD_URL=${url}  --link gemfire_${version}:gemfire aws_batch:v1.0.${version}

docker run -d -v $absoulteDir/web/log:/web/log -v $absoulteDir/web/config:/web/config --name web_${version} -p 8080:8080 aws_web:v1.0.${version}
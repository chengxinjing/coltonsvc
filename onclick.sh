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
   sudo newgrp docker
fi
sudo systemctl restart docker

git clone https://github.com/chengxinjing/coltonsvc.git
#build
cd ./coltonsvc
mvn clean package -Dmaven.test.skip=true

#build the aws_web
version=$1
cd ./aws_web
docker build -t aws_web:v1.0.${version} .  &

#build the aws_gemfire
cd ../aws_gemfire
docker build -t aws_gemfire:v1.0.${version} . &

#build the aws_batch

cd ../aws_batch
docker build -t aws_batch:v1.0.${version} . &


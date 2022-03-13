#!/bin/bash

git pull
git checkout .
git checkout $1
git pull
mvn clean package
docker rm -f ms_exchangerate
docker rmi ms_exchangerate
docker build -t ms_exchangerate .
docker run -p 8085:8085 --name ms_exchangerate --link RBC:database -d ms_exchangerate
docker ps -a

exit 0
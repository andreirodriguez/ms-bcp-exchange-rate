#!/bin/bash
git pull

mvn clean package -Dmaven.test.skip
docker rm -f ms_exchangerate
docker rmi ms_exchangerate
docker build -t ms_exchangerate .
docker run -p 8085:8085 --name ms_exchangerate -d ms_exchangerate
docker ps -a

exit 0
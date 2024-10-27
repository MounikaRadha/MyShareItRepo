#!/bin/bash
set -x
set -e

SERVER_HOST=$1

BUILD_FOLDER=MyShareIt
BUILD_ZIP=MyShareIt.zip


  sed -i "s/spring.datasource.url=jdbc:mysql:\/\/localhost:3306\/my_share_it?useSSL=false/spring.datasource.url=jdbc:mysql:\/\/my-shareit-db-v2.redacted.redacted.rds.amazonaws.com:3306\/my_share_it?useSSL=false/g" src/main/resources/application.properties
  sed -i "s/spring.datasource.password=root/spring.datasource.password=redacted/g" src/main/resources/application.properties
  sed -i "s/aws.serviceEndpoint=http:\/\/localhost:4567/aws.serviceEndpoint=https:\/\/s3.readacted-1.amazonaws.com/g" src/main/resources/application.properties
  sed -i "s/aws.accessKey=test/aws.accessKey=redacted/g" src/main/resources/application.properties
  sed -i "s/aws.secretKey=test/aws.secretKey=redacted/g" src/main/resources/application.properties



./gradlew bootJar
ls build/libs
echo 'Start creating build file to upload'

if [[ -d "$BUILD_FOLDER" ]]; then
  rm -rf "$BUILD_FOLDER"
fi

mkdir "$BUILD_FOLDER"

echo "=============List main build folder==========="

mv build/libs/MyShareIt-0.0.1-SNAPSHOT.jar build/libs/MyShareIt.jar
cp build/libs/MyShareIt.jar "$BUILD_FOLDER"

echo "=============List copied build folder==========="

zip -r "$BUILD_ZIP" "$BUILD_FOLDER"
rm -rf "$BUILD_FOLDER"

echo "Created build file to upload"

cat src/main/resources/application.properties
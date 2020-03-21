#!/bin/bash

./gradlew bootJar 
docker build -t covid19-community-data .

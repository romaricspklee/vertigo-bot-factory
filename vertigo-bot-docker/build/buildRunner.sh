#!/bin/bash

docker build -t vertigoio/bot-factory-runner -t vertigoio/bot-factory-runner:0.5.0 -f ./DockerfileRunner .

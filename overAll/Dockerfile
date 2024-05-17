FROM bellsoft/liberica-openjdk-alpine:17.0.8

#install curl and jq
RUN apk add --no-cache curl jq

# workspace
WORKDIR /home/selemium-docker

# copy the jar file
COPY target/docker-resources    ./
ADD runner.sh                   runner.sh

## environment variables
## ENV
## BROWSER
## SELENIUM_HUB_HOST
## SUITES
## THREAD_COUNT
#
## command for run tests
#ENTRYPOINT java -Dbrowser=${BROWSER} -Denv=${ENV} -DseleniumHubHost=${SELENIUM_HUB_HOST} \
#                                    -cp 'libs/*' RunTest \
#                                    ${SUITES} \
#                                    -threadcount=${THREAD_COUNT}

ENTRYPOINT sh runner.sh
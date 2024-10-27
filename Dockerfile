FROM container-registry.oracle.com/java/jdk-no-fee-term:21 as build

WORKDIR /opt

RUN mkdir /opt/app

ENV MIN $MIN
ENV MAX $MAX
ENV ENVIRONMENT $ENVIRONMENT

COPY MyShareIt.jar /opt/app/MyShareIt.jar
COPY docker/start.sh /opt/app/start.sh
COPY docker/entrypoint.sh /opt/app/entrypoint.sh
RUN chmod +x /opt/app/start.sh
RUN chmod +x /opt/app/entrypoint.sh

ENTRYPOINT ["/bin/bash", "/opt/app/entrypoint.sh"]


FROM amazoncorretto:21-alpine3.20
RUN addgroup -g 1024 mygroup \
    && adduser -D -u 1024 -G mygroup myuser
RUN mkdir -p /opt/accounting-test/log
USER myuser
COPY build/libs/*.jar /opt/accounting/app/accounting.jar
VOLUME ["/opt/accounting/conf", "/opt/accounting/log:Z"]
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=test", "-Dspring.config.additional-location=/opt/accounting/conf/application-test.yml", "/opt/accounting/app/accounting.jar"]
FROM openjdk:21
COPY ./*.jar /opt/accounting/app/accounting.jar
VOLUME /opt/accounting/conf
VOLUME /opt/accounting/log
ENTRYPOINT ["java","-jar","/opt/accounting/app/accounting.jar","--spring.profiles.active=test"]
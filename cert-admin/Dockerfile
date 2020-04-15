FROM hub.c.163.com/library/java:latest
VOLUME /tmp
ADD target/cert-admin-0.0.1.jar cert-admin.jar
ENTRYPOINT ["java","-jar","/cert-admin.jar"]

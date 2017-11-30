FROM qianchun27/centos7-jdk8:0.0.1
MAINTAINER qianchun, qinachun@huli.com
ENTRYPOINT ["java", "-jar", "/wind/server/wind-user-service-0.0.1-SNAPSHOT.jar"]
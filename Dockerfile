FROM qianchun27/centos7-jdk8:0.0.1

MAINTAINER qianchun, qinachun@huli.com

RUN yum install -y git;
RUN yum install -y maven;

RUN cd /; git clone https://github.com/wind27/wind-auth-web.git -b master; cd wind-auth-web;

#mvn clean package --settings /wind-auth-web/settings.xml;
#ENTRYPOINT ["java", "-jar", "/wind-auth-web/target/wind-auth-web-0.0.1-SNAPSHOT.jar"]
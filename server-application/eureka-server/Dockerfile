FROM ioxiaowei/apline3-jdk8:latest
MAINTAINER xiaowei
WORKDIR /root
ADD /target/*.jar /root/app.jar
EXPOSE 8761
ENTRYPOINT ["java","-Dfile.encoding=utf-8","-jar","/root/app.jar"]
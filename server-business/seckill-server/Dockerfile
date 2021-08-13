FROM ioxiaowei/centos7-jdk8:latest
MAINTAINER xiaowei
WORKDIR /root
ADD /target/*.jar /root/app.jar

EXPOSE 8080
ENV JAVA_OPTS="\
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/opt/heap/ \
-Xloggc:/opt/gc-%t.log \
-XX:+PrintGCDateStamps"

ENTRYPOINT ["sh","-c","java ${JAVA_OPTS} -Dfile.encoding=utf-8 -jar /root/app.jar"]
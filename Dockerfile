FROM jfrog.onebank.local/oracle/serverjre:1.8.0_241-b07
VOLUME /tmp
VOLUME /logs
VOLUME /home/java/config
EXPOSE 7084
ADD target/*.jar /home/java/app.jar
ENV JAVA_OPTS="-Dspring.config.additional-location=/home/java/config/secrets.yml,/home/java/config/application.yml -Dlog4j2.configurationFile=/home/java/config/log4j2.xml"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /home/java/app.jar" ]
FROM java:8
VOLUME /tmp
ADD discovery-eureka-0.0.1-SNAPSHOT.jar descovery.jar
RUN bash -c 'touch /descovery.jar'
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/descovery.jar"]
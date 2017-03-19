FROM java:8
VOLUME /tmp
ADD provider-user-0.0.1-SNAPSHOT.jar user.jar
RUN bash -c 'touch /user.jar'
EXPOSE 8000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user.jar"]
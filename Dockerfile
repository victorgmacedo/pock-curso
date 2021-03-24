FROM openjdk
COPY target/curso-0.0.1-SNAPSHOT.jar /var/app/cep.jar
WORKDIR var/app/
ENTRYPOINT java -jar cep.jar
EXPOSE 8080
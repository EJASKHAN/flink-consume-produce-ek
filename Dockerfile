#author Ejaskhan
#step 1 : generate the job.jar
FROM maven:3.6-jdk-8-slim AS builder
COPY . ./src/app
WORKDIR /src/app
RUN mvn clean install
#step2 : copy job.jar to the base flink image of desired version
FROM apache/flink:1.12.1-scala_2.11-java8
WORKDIR /opt/flink/
#copy job.jar to the container directory /opt/flink/usrlib
COPY --from=builder /src/app/target/flink-consume-produce-ek-1.0-SNAPSHOT.jar /opt/flink/usrlib/artifacts/app.jar
#provide permission for the config files in /opt/flink/conf
WORKDIR /opt/flink/conf/
RUN chown flink:flink /opt/flink/conf/flink-*
FROM ubuntu:latest

RUN apt-get update && \
 apt-get install -y default-jdk

ADD ./target/. /home/flight-controller/target

CMD java -jar /home/flight-controller/target/flight-controller-0.1.0.jar

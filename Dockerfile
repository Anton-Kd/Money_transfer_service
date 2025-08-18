FROM openjdk:17

EXPOSE 5500

ADD target/Money_transfer_service-0.0.1-SNAPSHOT.jar moneyTransfer.jar

ENTRYPOINT ["java", "-jar", "moneyTransfer.jar"]
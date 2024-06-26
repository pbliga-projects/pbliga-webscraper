# Use a smaller base image
FROM gradle:6.8.3-jdk8 as base

RUN mkdir -p /application

WORKDIR /application
COPY . ./

FROM base as build
RUN gradle build --no-daemon

FROM base as development
WORKDIR /application
EXPOSE 5005
CMD ["gradle", "bootRun", "-Pdebug"]

FROM gradle:6.8.3-jdk8 as production

RUN mkdir -p /application/datastorage
ARG JAR_FILE=/application/build/libs/pbliga-webscraper.jar
COPY --from=build ${JAR_FILE} /application/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/application/app.jar"]
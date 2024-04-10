FROM gradle:8-jdk8 as base

RUN mkdir -p /application

WORKDIR /application
COPY . ./

FROM base as build
#RUN gradle --refresh-dependencies
RUN gradle build --no-daemon

FROM base as development
WORKDIR /application
EXPOSE 5005
CMD ["gradle", "bootRun", "-Pdebug"]


FROM openjdk:8-alpine as production

RUN mkdir -p /application/datastorage
ARG JAR_FILE=/application/build/libs/pbliga-webscraper.jar
COPY --from=build ${JAR_FILE} /application/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/application/app.jar"]

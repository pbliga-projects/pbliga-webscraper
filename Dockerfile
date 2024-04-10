FROM gradle:8-jdk8 as base

RUN mkdir -p /application

#RUN /usr/sbin/addgroup --system juser
#RUN /usr/sbin/adduser -S -s /bin/false -G juser juser
#RUN chown -R juser:juser /application
#USER juser

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
RUN rm -rf /application/build

EXPOSE 8080
ENTRYPOINT ["java","-jar","/application/app.jar"]

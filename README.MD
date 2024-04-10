Docker run:
  * `docker run -it -p 8080:8080 -v c:/Users/Siarhei_Dubadzelau/Documents/Projects/PB-Projects/:/application/datastorage`
  * `docker run -p 8080:8080 -v C:/Users/Siarhei_Dubadzelau/Documents/Projects/PB-Projects:/application/datastorage --name pb-liga-dataloader pbliga_webscraper_image:latest`
  * `docker run -p 4444:4444 --name Selenium-Standalone-Chrome -d selenium/standalone-chrome:latest` 

Docker Compose Rebuild and start all:
  * `docker-compose up --detach --build`
  * 
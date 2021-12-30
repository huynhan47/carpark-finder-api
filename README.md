# carpark-finder-api
A small car park finder api

1.  Business requirement:
Client input their latitude and longitude data, with paging information also, server response a subset of Car Parking available nearest to them.
Car Park data scope is in Singapore.

2. Basic solution flow 
  - 2.1 Get static carpark infomation from https://data.gov.sg/dataset/hdb-carpark-information as csv file and import to database.
  - 2.2 Get car park available live data from https://api.data.gov.sg/v1/transport/carpark-availability.
  - 2.3 Calculate distance base on coordinates data input and return car park available information.

3. Detail implementation flow
- 3.1 Get static carpark infomation data in csv format. Create table in database use MySql to store data. Coordinates are in SVY21 format, use this tool to batch convert to widely used WGS84 (latitude,longitude) format https://dominoc925-pages.appspot.com/webapp/calc_svy21/default.html.
- 3.2 Create another table to store live available car park.
- 3.3 Create Database function to calculate distance base on latitude, longitude inputs. (Reference https://stackoverflow.com/questions/13716313/mysql-function-to-calculate-distance-between-two-latitudes-and-longitudes)
- 3.4 Write feature to update car park avalability to database, use dataset from https://api.data.gov.sg/v1/transport/carpark-availability
- 3.5 Write a RESTful API feature to execute "3.4" update feature.
- 3.6 Write a feature to find nearest car park availability from latitude,longitude inputs
- 3.7 Write a RESTful API to mapping to "3.6" feature.

4. How to make project runnable
- Use MySQL backup file at src\main\resources\parkingdb to import database structure and data.
- Modified database connection information in src\main\resources\META-INF as your environtment information.
- Build project to .war file and deploy to a Java Wep Application Server like Tomcat (or to .jar file and make a self running Wep Application Server inside).
- Use an API testing tool to check result.

5. API usage for quickly checking result
  I've already deploy API to http://45.76.98.33:8080/carparks
  Let check API by try something like below in API testing tool (Postman).
  - http://45.76.98.33:8080/carparks/update : For update live car park information, return HTTP Status 200 (OK) when success
  - http://45.76.98.33:8080/carparks/nearest?latitude=1.36670794137058&longitude=103.841712811889&page=4&per_page=5 : For finding nearest car park available base on coordinates input.

6. Technical keywork use
  Java, MySQL, Spring, SpringBoot, JPA, RESTful API, Maven.
  




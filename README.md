# carpark-finder-api
A small car park finder api for demo to wego interview

1.  Business requirement:
Client input their latitude and longitude data, with paging information also, server response a subset of Car Parking available nearest to them.
Car Park data scope is in Singapore.

2. Basic solution flow 
  - 2.1 Get static carpark infomation from https://data.gov.sg/dataset/hdb-carpark-information as csv file and import to database.
  - 2.2 Get car park available live data from https://api.data.gov.sg/v1/transport/carpark-availability.
  - 2.3 Calculate distance base on coordinates data input and return car park available information.

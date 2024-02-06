# Report-service
## Prerequisites
1. Java 21
2. Docker

## Run Test
1. Change directory to projectRoot
   ```bash
   cd /path/to/projectRoot
   ```

2. Run the following command to execute all tests using Maven:
   ```bash
   ./mvnw test
   ```
   
## Content 
1. Application
   ```bash
   cd /path/to/projectRoot
   docker-compose up -d
   ```
   
2. Daily summary report Rest Api endpoint
   ```
   GET localhost:8088/dailySummaryReportCsv
   ```

3. Output.csv
   ```
   /path/to/projectRoot/Output.csv
   ```


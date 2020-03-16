# Walmart_DLS_QEChallenge Assumptions
Walmart Drone Launch Service Challenge

## Design Diagram												

![Design Diagram](https://github.com/lokeshgnanasekhar/Walmart_DLS_QEChallenge/blob/master/Drone%20Delivery%20QE%20Challenge%20(5).png)
 







## DLS Modules											



In a given Drone Launch Scheduling eco-system we have following Main Modules

-	Drone Orders DB

-	Drone – Delivery Services (API Layer)

-	DLS (Drone Launch Scheduling) Micro Service Layer 
    - DLS Core
    - DDTC (Drone Delivery Time Calculator) 
    - NPS (Net Promoter Score)Engine
    - DLS Generator
    - DLS Access
    - Analytics

-	DLS (Drone Launch Scheduling) Interface




The following are supporting modules which feed the Main Modules

- Drone Order Queue Server / Feed Server
-	Order DB
-	Order Management Module
-	Client Channel


<br/><br/>
## Module Definitions


### Main Modules

| Module   | Description  |
| ------------- | ------------- |
| **Drone Orders DB**  | This Holds the Drone related order details along with customer details. This database is also responsible for maintaining drones schedule - delivery times , NPS etc.,  |
| **Drone – Delivery Services (API Layer)**  | This Layer interacts DLS Micro service Layer and database in order to provide and process data.   |
| **DLS Micro Service Layer**  | 	This Layer has following services<br/><br/> **DLS Core** : This core module is responsible for managing orders ,drone availability, scheduling drone departures.<br/><br/>**DDTC (Drone Delivery Time Calculator)** : This module calculates the Drone Delivery times based on the business logic.<br/><br/>**NPS Business Engine**  :Calculating the Net Promoter score based on the customer feedbacks.<br/><br/>**DLS Generator** :  It is responsible to generate and output the Drone Orders along with schedules and NPS .<br/><br/>**DLS Access** : To fetch orders, Location and other customer details needed.<br/><br/>**Analytics** : Which is for analyzing number of customers doing drone orders , the location which has more orders , frequency of orders and others.<br/>   |
| **DLS (Drone Launch Scheduling) Interface**   |  This is a web interface which interacts with DLS Micro service layer for getting the Drone schedules , NPS Score etc.,   |



### Supporting Modules

| Module   | Description  |
| ------------- | ------------- |
| **Drone Order Queue Server / Feed Server**  | This Module is responsible to maintain a queue for the drone orders placed by customers. The queue will fetch all the necessary info for the Drone delivery system through drone databases and services  |
| **Order DB**  | This Database will hold all the orders related data .   |
| **Order Management Module**  | Handles all the orders coming from various channels and process those orders based on inventory availability   |   |
| **Client Channel**  | Client Channel is the customer facing module for placing the orders   \
   
<br/><br/>
## Work Process Description:

The customer interacts with order placement channels like .com or any handheld devices to place the order. Once the order is placed it will flow through the order management and to the order database. A queue server will receive the Drone orders from the orders database. This server will queue all the orders and pushes to the Drone Database once the order is ready to be delivered by the Drone.

All interactions with the database are handled via the Drone Delivery Service (DDS)API layer. The API layer is responsible to provide necessary information for the DLS Micro service Layer requesting it. The DLS Micro Service Layer which has the DLS Core ,Net promoter score business logic, Drone Delivery Time Calculator (DDTC), DLS Generator, DLS Access will in turn provide data to DLS interface. All DLS Micro service Layer calculations and information are stored in Drone DB.

Once the DLS interface fed with input having Order number, Location and Order time eg: WM001 N11W5 05:11:50 , then the interface calls necessary services to get the Drone Schedules. DLS core will do Drone schedules to dispatch the order based on the order timestamp .

NPS Business Engine gets all the necessary details from the Drone DB in order to calculate the Net Promoter score based on the business logic.

Finally , After everything is calculated and updated  DLS Generator will generate a file with records in format - WM004 06:31:24 which is order number and Departure time along with NPS as the last record.






# 🌍 Project Aegis - First Contact Hackathon

### The Scenario
Humanity's first contact began with a total communications blackout. The global grid is down. We lost the internet. 

### The Solution: Offline-First Mesh Network
Project Aegis rebuilds local communication. It turns community safe zones (hospitals, schools) into standalone network nodes using **Java Spring Boot**, **MySQL**, and **Angular**. 

Survivors connect to the local node to chat in real-time. To communicate with other settlements, Aegis utilizes a **"Sneakernet Sync Protocol"**—exporting encrypted JSON data to physical USB drives carried by couriers, merging databases asynchronously across the wasteland without internet access.

### How to Run Locally
1. Run the Spring Boot backend on port 8080 (Node Alpha).
2. Start the Angular frontend via `ng serve`.
3. Broadcast messages to the local LAN, and test the offline sync by exporting/importing the `.json` files.

# GmailSafe v.0.2  

https://github.com/vdomrachev/GmailSafe

To build application use "mvn clean package". Java 11 and Maven is required.

To use application 
1. Visit https://developers.google.com/gmail/api/quickstart/java to turn on Gmail API and download credentials.json file.
2. Create application.properties file with content "credentialsFilePath=#path to credentials.json#"
3. Run "java -jar target/gmail-safe-jar-with-dependencies.jar #path to application.properties file#"
4. Use GET http://localhost:8080/gmail-safe/backups to list backups
5. use POST http://localhost:8080/gmail-safe/backups to start backup. Currently backups only 10 messages from Gmail. On first run you will have to provide access to application "Quickstart" in Google account.
6. While backup is running you can check status by get calling GET http://localhost:8080/gmail-safe/backups

Response example:
[
	{
		"backupId": "868f2021-a5bc-47bd-bd1f-699af59150fd",
		"date": "Mar 11, 2021, 9:59:21 PM",
		"status": "IN_PROGRESS"
	}
]

When backup is finished (status=OK) you can download zip content of backup by calling
GET http://localhost:8080/gmail-safe/export/{backupId}
Service returns data.zip file with one backup.json file in it representing Backup object json content with list of Gmail messages.

Return all emails with the specified label in a specified backup, in a compressed archive
GET http://localhost:8080/gmail-safe/export/{backupId}/{label}
For example GET http://localhost:8080/gmail-safe/export/90c761b6-bb90-4058-8922-fb0000c8aaf5/SENT
Service returns data.zip file with one backup.json file in it representing Backup object json content with list of Gmail messages.
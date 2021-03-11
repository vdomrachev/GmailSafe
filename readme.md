GmailSafe v.0.1   https://github.com/vdomrachev/GmailSafe

To build application use "mvn clean package". Java 11 and Maven is required.
To use application 
	0. https://developers.google.com/gmail/api/quickstart/java to turn on Gmail API and download credentials.json file.
	1. run "java -jar target/gmail-safe-jar-with-dependencies.jar {path to credentials.json file}"
	2. use GET http://localhost:8080/gmail-safe/backups to list backups
	3. use POST http://localhost:8080/gmail-safe/backups to start backup. Currently backups only 10 messages from Gmail. On first run you will have to provide access to application "Quickstart" in Google account.
	4. While backup is running you can check status by get calling GET http://localhost:8080/gmail-safe/backups
	[
		{
			"backupId": "868f2021-a5bc-47bd-bd1f-699af59150fd",
			"date": "Mar 11, 2021, 9:59:21 PM",
			"status": "IN_PROGRESS"
		}
	]
	5. When backup is finished (status=OK) you can download zip content of backup by calling
	GET http://localhost:8080/gmail-safe/export/{backupId}
	Thats a data.zip file with one backup.json file in it representing Backup object json content.

























GET http://localhost:8080/gmail-safe/backups
POST http://localhost:8080/gmail-safe/backups

https://blog.mailtrap.io/send-emails-with-gmail-api/


https://plswiderski.medium.com/google-api-authentication-with-oauth-2-on-the-example-of-gmail-a103c897fd98


Client ID
182175281326-lr9uqlu7cnptj39umvsmbd4dcvp1mn7c.apps.googleusercontent.com
Client secret
TrBDn1yhB2r1U6ubTvFyLl-8
Creation date
March 10, 2021 at 8:35:20 PM GMT+3


https://accounts.google.com/o/oauth2/v2/auth?client_id=182175281326-lr9uqlu7cnptj39umvsmbd4dcvp1mn7c.apps.googleusercontent.com&response_type=code&scope=https://www.googleapis.com/auth/gmail.readonly&redirect_uri=http://localhost&access_type=offline


http://localhost/?code=4/0AY0e-g7WgPgSS591cVhx8L-53kKEoQm6f7-UfncZaDQqiv6viacWWX-fZ4a6-bgXxPNeIw&scope=https://www.googleapis.com/auth/gmail.readonly


POST https://www.googleapis.com/oauth2/v4/token

code=4/0AY0e-g7WgPgSS591cVhx8L-53kKEoQm6f7-UfncZaDQqiv6viacWWX-fZ4a6-bgXxPNeIw#&client_id=182175281326-lr9uqlu7cnptj39umvsmbd4dcvp1mn7c.apps.googleusercontent.com&client_secret=TrBDn1yhB2r1U6ubTvFyLl-8&grant_type=authorization_code&redirect_uri=http://localhost




{
    "access_token": "ya29.a0AfH6SMCKNGgffOgM--5tsiP-40Z5DhBfJAp8z9qiuq_tJVRghZ-65FX4vhMp0XsaJrnbiEpWRFWHyYApdf9d4HnTsxnCE3xdIpXTwvCOUyXBNBMx8niXtLfHrjlxoi8uq9AZrGrsmPzZp1eCniWrBL770v08",
    "expires_in": 3599,
    "refresh_token": "1//0cju99wTWXnBsCgYIARAAGAwSNwF-L9Ir4wkIhY6a6O2xhFhAPNy7pugEhZ1D9CYW4DH8wu8FkaBVxP-IXBkFI7v6rFiR2qBGXyY",
    "scope": "https://www.googleapis.com/auth/gmail.readonly",
    "token_type": "Bearer"
}


https://developers.google.com/gmail/api/quickstart/java
It was the first web page that i walked through and used that code in GmailQuickstart class in my project .
But i was a little bit confused by using com.google.oauth-client:google-oauth-client-jetty with google-api-client and google-api-services-gmail because of non-functional requirement "The application’s source code does NOT depend on .... except... a Gmail client library" and already not using application server for app deployment (some misunderstood).
So i found example (as i see more complicated) where google-auth-library-oauth2-http was used (not using jetty).
Now i tried with https://developers.google.com/gmail/api/quickstart/java and it works out of the box nicely. Will continue with a project in the evening after my work.

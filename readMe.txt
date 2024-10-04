API Autmation Test:

*Disclaimer*

First, I am grateful for this opportunity. Sorry, there are a lot of imperfect. To be honest, 
I'm not familiar with selenium because I usually use automation tools such as Katalon & Tosca.
Ok, I will explain a little about this codeFirst, I am grateful for this opportunity. 
Sorry, there are a lot of imperfect. To be honest, I'm not familiar with selenium because 
I usually use automation tools such as Katalon & Tosca.


But anyway, let me explain a little about this code

1. To set te token, open cmd and use this token:
set GOREST_TOKEN=279713ccf8c4aea4edcc692a8fda3ea567fea42f6a7d7274aae3e296ef9ca701

2. System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
This line sets the system property for the ChromeDriver executable, which allows Selenium to control the 
Chrome browser. The path should point to the location where the ChromeDriver is installed on your machine.

3. // GoRest API endpoint URL for creating a new user
driver.get("https://gorest.co.in/");
This line opens the GoRest homepage in the browser. Although this is included, it may not be necessary if the 
script is solely for making API calls without user interface interaction.

4. // GoRest API endpoint URL for deleting a user
// Replace {id} with the user ID you want to delete (e.g., 1234)
String userId = "6941707"; // Specify the user ID to delete
String apiUrl = "https://gorest.co.in/public/v2/users/" + userId;
Setting up the API URL: Here, the user ID to be deleted is specified. This line constructs the API URL by 
appending the user ID to the base URL for deleting users in the GoRest API.

5. if (accessToken == null || accessToken.isEmpty()) {
    System.out.println("Error: GOREST_TOKEN environment variable not set.");
    return; // Exit if the token is not set
}
Token validation: This conditional block checks whether the accessToken is null or empty. If it is, an error 
message is printed, and the program exits. This prevents unauthorized requests to the API.

6. // Set the request method to DELETE
conn.setRequestMethod("DELETE");
Set the request method: This line determines that the request method is deleted, but you can change the 
request according to the required request. For example: PUT to edit/update, get to retrieve data & post to 
create. The entry of the request method: This line determines that the request method is deleted, which is 
used to delete users identified by the user ID in the API url.

7.  // JSON request body for creating a new user
        String jsonInputString = "{"
                + "\"name\": \" \","
                + "\"gender\": \" \","
                + "\"email\": \" \","
                + "\"status\": \" \""
                + "}";
For method PUT & POST we need to send the body. This is the body/data that we submit and or update

8. // Set the request method to POST
   conn.setRequestMethod("POST");
            // Enable the ability to send a body with the request
            conn.setDoOutput(true);
// Write the JSON body to the request
try (OutputStream os = conn.getOutputStream()) {
    byte[] input = jsonInputString.getBytes("utf-8");
    os.write(input, 0, input.length);
}
this for POST & PUT method because they send JSON body to: 
Getting the Output Stream: conn.getOutputStream() returns an output stream for writing data to the connection.
Converting JSON to Bytes: jsonInputString.getBytes("utf-8") converts the JSON string into a byte array using 
UTF-8 encoding. This is necessary because the output stream only works with byte data.
Writing Data to the Output Stream: os.write(input, 0, input.length) writes the byte array to the output 
stream. This is where the JSON data is sent to the server as part of the POST request.
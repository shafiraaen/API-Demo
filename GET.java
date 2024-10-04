package seleniumpackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;

public class GET {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// GoRest API endpoint URL for creating a new user
		driver.get("https://gorest.co.in/");
		
		 String userId = "6941707"; // Specify the user ID to retrieve
	        String apiUrl = "https://gorest.co.in/public/v2/users/" + userId;

	        // Retrieve the Bearer Token from the environment variable
	        String accessToken = System.getenv("GOREST_TOKEN");

	        if (accessToken == null || accessToken.isEmpty()) {
	            System.out.println("Error: GOREST_TOKEN environment variable not set.");
	            return; // Exit if the token is not set
	        }	

	        try {
	            // Create a URL object
	            URL url = new URL(apiUrl);

	            // Open a connection to the URL
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	            // Set the request method to GET
	            conn.setRequestMethod("GET");

	            // Set headers for the request
	            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	            conn.setRequestProperty("Accept", "application/json");

	            // Get the response code
	            int responseCode = conn.getResponseCode();
	            System.out.println("GET Response Code: " + responseCode);

	            // Read the response
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                String inputLine;
	                StringBuilder response = new StringBuilder();

	                while ((inputLine = in.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                in.close();

	                // Print the response
	                System.out.println("User Information: " + response.toString());
	            } else {
	                System.out.println("Failed to retrieve user. Response Code: " + responseCode);
	            }

	            // Close the connection
	            conn.disconnect();

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the WebDriver instance
	            driver.quit();
	        }
	    }
	}



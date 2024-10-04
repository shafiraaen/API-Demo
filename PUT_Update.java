package seleniumpackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;

public class PUT_Update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		// GoRest API endpoint URL for creating a new user
		driver.get("https://gorest.co.in/");

        // GoRest API endpoint URL for updating a user
        // Replace {id} with the user ID you want to update (e.g., 1234)
        String userId = "6941707"; // Specify the user ID to update
        String apiUrl = "https://gorest.co.in/public/v2/users/" + userId;

        // Retrieve the Bearer Token from the environment variable
        String accessToken = System.getenv("GOREST_TOKEN");

        if (accessToken == null || accessToken.isEmpty()) {
            System.out.println("Error: GOREST_TOKEN environment variable not set.");
            return; // Exit if the token is not set
        }

        // JSON request body for updating a user
        String jsonInputString = "{"
                + "\"name\": \"Shafira Mutiara N\","
                + "\"gender\": \"Female\","
                + "\"email\": \"shafiraen@gmail.com\","
                + "\"status\": \"active\""
                + "}";

        try {
            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request method to PUT
            conn.setRequestMethod("PUT");

            // Set headers for the request
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");

            // Enable the ability to send a body with the request
            conn.setDoOutput(true);

            // Write the JSON body to the request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = conn.getResponseCode();
            System.out.println("PUT Response Code: " + responseCode);

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


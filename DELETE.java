package seleniumpackage;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;

public class DELETE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		// GoRest API endpoint URL for creating a new user
		driver.get("https://gorest.co.in/");
		

		// GoRest API endpoint URL for deleting a user
        // Replace {id} with the user ID you want to delete (e.g., 1234)
        String userId = "6941707"; // Specify the user ID to delete
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

            // Set the request method to DELETE
            conn.setRequestMethod("DELETE");

            // Set headers for the request
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestProperty("Accept", "application/json");

            // Get the response code
            int responseCode = conn.getResponseCode();
            System.out.println("DELETE Response Code: " + responseCode);

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



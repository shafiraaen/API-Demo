package seleniumpackage;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;

public class POST_Create_Negatif {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://gorest.co.in/");

        // GoRest API endpoint URL for creating a new user
        String apiUrl = "https://gorest.co.in/public/v2/users";

        // Retrieve the Bearer Token from the environment variable
        String accessToken = System.getenv("GOREST_TOKEN");
        System.out.println("Access Token: " + accessToken);  // Debugging statement

        if (accessToken == null || accessToken.isEmpty()) {
            System.out.println("Error: GOREST_TOKEN environment variable not set.");
            driver.quit();  // Close the WebDriver before returning
            return; // Exit if the token is not set
        }

        // JSON request body for creating a new user
        String jsonInputString = "{"
                + "\"name\": \"Shafira Mutiara\","
                + "\"gender\": \"Female\","
                + "\"email\": \" \","
                + "\"status\": \"retire\""
                + "}";

        try {
            // Create a URL object
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            conn.setRequestMethod("POST");

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
            System.out.println("POST Response Code: " + responseCode);

            // Check if the request was successful
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("User created successfully!");
            } else {
                System.out.println("Failed to create user. Response Code: " + responseCode);
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

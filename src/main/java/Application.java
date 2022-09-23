import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.zipcode.data.dto.ZipCodeDTO;
import com.kenzie.app.zipcode.format.AddressFormatter;
import com.kenzie.app.zipcode.http.HTTPConnector;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        // Declare variables
        try {
            // Comment for pushing
            final String BASE_URL = "https://api.zippopotam.us/us/";
            Scanner scanner = new Scanner(System.in);
            String recipientName;
            String streetAddress;
            String city;
            String state = "";
            String zipCode;

            // Read in user input - scanner
            System.out.println("Enter recipient name: ");
            recipientName = scanner.nextLine();

            System.out.println("Enter street address: ");
            streetAddress = scanner.nextLine();

            System.out.println("Enter city: ");
            city = scanner.nextLine();

            System.out.println("Enter state: ");
            if (scanner.nextLine().length() > 2) {
                System.out.println("Please use the two letter state standard.");
                System.out.println("Enter state: ");
            }
            state = AddressFormatter.formatAddress(scanner.nextLine());


            // Replace spaces in city - Los Angeles
            String tempCity = city.replaceAll(" ", "%20");

            // format URL with user city and state
            String finalURL = BASE_URL + state + "/" + tempCity;
            // System.out.println(finalURL);

            // Call GET
            String httpResponse = HTTPConnector.makeGETRequest(finalURL);
            // System.out.println(httpResponse);

            // if return contains 404, don't object map
            if (httpResponse.contains("GET request failed")) {
                System.out.println("No zip code found");
                zipCode = "";
            } else {
                // ObjectMapper to retrieve zipcode
                // 1. Instantiate ObjectMapper
                ObjectMapper objectMapper = new ObjectMapper();
                // 2. Declare DTO
                ZipCodeDTO zipObj;
                // 3. Set DTO to objectMapper.readValue()
                zipObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);

                // zipObj.getPlaces().get(0);
                // if 1 zipCode returned, set zipCode
                if (zipObj.getPlaces().size() == 1) {
                    zipCode = zipObj.getPlaces().get(0).getPost_code();
                } else if (zipObj.getPlaces().size() > 1) {
                    // else loop and display all zipcodes
                    for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                        System.out.println(i + ") " + zipObj.getPlaces().get(i).getPost_code());
                    }
                }
                // prompt user to select zipcode
                System.out.println("Choose a zipcode: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                // set zipCode based on selection
                zipCode = zipObj.getPlaces().get(choice).getPost_code();
                // System.out.println(zipCode);
            }
            // Print out final address
            System.out.println(AddressFormatter.formatAddress(recipientName));
            System.out.println(AddressFormatter.formatStreetAddress(streetAddress));
            System.out.println(AddressFormatter.formatAddress(city + " " + state + " " + zipCode));
        }
        catch (Exception e) {
            System.out.println("Unexpected exception: " + e);
        }
    }

    // Back up working main
    public static void main_backup(String[] args) {
        try {
            // API - https://api.zippopotam.us/us/ca/LOS%20ANGELES
            // declare variables
            final String TEST_URL = "https://api.zippopotam.us/us/ca/LOS%20ANGELES";
            String httpResponseStr;

            // Connect to zippopotam.us and get zipCode
            httpResponseStr = HTTPConnector.makeGETRequest(TEST_URL);
            System.out.println(httpResponseStr);

            // 1. ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // 2. Declare DTO  object
            ZipCodeDTO zipObj;

            // 3. Read Data - readValue()
            zipObj = objectMapper.readValue(httpResponseStr, ZipCodeDTO.class);

            // Print out place name, zip code, and state
            System.out.println("City: " + zipObj.getPlaces().get(0).getPlace_name());
            System.out.println("Zip Code: " + zipObj.getPlaces().get(0).getPost_code());
            System.out.println("State: " + zipObj.getState());

            // Check if more than 1
            if (zipObj.getPlaces().size() > 0) {
                // Loop and print out the list of zipcodes
                for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                    // Print out place name, zip code, and state
                    System.out.println("City: " + zipObj.getPlaces().get(i).getPlace_name());
                    System.out.println("ZipCode: " + zipObj.getPlaces().get(i).getPost_code());
                    System.out.println("State: " + zipObj.getState());
                }
            }

        } catch (Exception e) {
            System.out.println("Unexpected exception" + e.getMessage());
        }
    }

}

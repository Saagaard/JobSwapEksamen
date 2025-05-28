package org.eksamen.jobswap.businessServices;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.*;
import org.eksamen.jobswap.domain.TransportDetails;
import org.eksamen.jobswap.foundation.CredentialsManager;

/**
 * Service class responsible for calculating the distance between 2 adresses
 */
public class CalculateTransportImpl implements CalculateTransport {

    private static final String API_KEY = CredentialsManager.getMapsApiKey();

    /**
     * @param homeAddress An Employee's home address
     * @param workAddress An Employee's work address
     * @param homeZip A Zip object for an Employee's home address
     * @param workZip A Zip object for an Employee's work address
     * @return {@link TransportDetails} with the distance and duration between the 2 adresses
     * @throws Exception If an error occurs during data retrieval
     */
    public TransportDetails calculateTransportDetails(String homeAddress, String workAddress, int homeZip, int workZip) throws Exception {

        String url = "https://routes.googleapis.com/directions/v2:computeRoutes?key=" + API_KEY;

        String homeZipString = String.valueOf(homeZip);
        String workZipString = String.valueOf(workZip);

        String originAddress = homeAddress + " " + homeZipString;
        String destinationAddress = workAddress + " " + workZipString;

        String requestBody = """
    {
      "origin": {
        "address": "%s"
      },
      "destination": {
        "address": "%s"
      },
      "travelMode": "DRIVE",
      "routingPreference": "TRAFFIC_AWARE"
    }
    """.formatted(originAddress, destinationAddress);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .header("X-Goog-FieldMask", "routes.duration,routes.distanceMeters")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject route = json.getAsJsonArray("routes").get(0).getAsJsonObject();


            String durationString = route.get("duration").getAsString();
            int duration = Integer.parseInt(durationString.replace("s","")) / 60; // fjern "S" fra output, og divider ned til minutter.
            int distanceMeters = route.get("distanceMeters").getAsInt();


            return new TransportDetails(duration, distanceMeters);
        }
        else {
            throw new Exception("Fejl i transportDetails, fejlkode: " + response.statusCode() + "\n" + response.body());
        }
    }
}

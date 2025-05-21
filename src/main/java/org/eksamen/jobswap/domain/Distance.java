package org.eksamen.jobswap.domain;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.*;
import org.eksamen.jobswap.foundation.CredentialsManager;


public class Distance {

    private static final String API_KEY = CredentialsManager.getMapsApiKey();

    public static void getTransportDetails(String homeAddress, String workAddress) throws Exception {
        String origin = homeAddress;
        String destination = workAddress;

        String url = "https://routes.googleapis.com/directions/v2:computeRoutes?key=" + API_KEY;

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
    """.formatted(origin, destination);

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

            String duration = route.get("duration").getAsString();
            int distanceMeters = route.get("distanceMeters").getAsInt();

            System.out.println("Distance: " + (distanceMeters / 1000.0) + " km");
            System.out.println("Duration (sekunder): " + duration);
        } else {
            System.out.println("Fejl: " + response.statusCode());
            System.out.println(response.body());
        }
    }
}

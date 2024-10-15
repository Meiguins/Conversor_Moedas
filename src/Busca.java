import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Busca{
    public static void main(String[] args) throws IOException, InterruptedException{
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o valor que deseja converter");
        Double valorDigitado = sc.nextDouble();

        System.out.println("Digite para qual moeda deseja converter: ");
        var moeda = sc.nextLine();

        String endereco = "https://v6.exchangerate-api.com/v6/93372b04d48a3b502e00f6e4/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}

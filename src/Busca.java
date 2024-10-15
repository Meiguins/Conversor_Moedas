import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Busca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o valor que deseja converter");
        Double valorDigitado = sc.nextDouble();
        sc.nextLine(); // Consumir a nova linha pendente

        System.out.println("Digite para qual moeda deseja converter: ");
        String moeda = sc.nextLine().toUpperCase();

        String endereco = "https://v6.exchangerate-api.com/v6/93372b04d48a3b502e00f6e4/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Converter a resposta JSON em objeto Java
        Gson gson = new Gson();
        ExchangeRatesResponse ratesResponse = gson.fromJson(response.body(), ExchangeRatesResponse.class);

        // Verificar se a moeda solicitada está presente na resposta
        if (ratesResponse.conversion_rates.containsKey(moeda)) {
            Double taxa = ratesResponse.conversion_rates.get(moeda);
            Double valorConvertido = valorDigitado * taxa;
            System.out.printf("O valor %.2f USD convertido para %s é: %.2f%n", valorDigitado, moeda, valorConvertido);
        } else {
            System.out.println("Moeda não encontrada.");
        }
    }
}

class ExchangeRatesResponse {
    String base_code;
    Map<String, Double> conversion_rates;
}
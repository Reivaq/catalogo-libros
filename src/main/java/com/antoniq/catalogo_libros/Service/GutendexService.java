package com.antoniq.catalogo_libros.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Service
public class GutendexService {

    private final OkHttpClient client = new OkHttpClient();
    private final String BASE_URL = "https://gutendex.com/books?search=";

    public String buscarLibros(String query) {
        String url = BASE_URL + query.replace(" ", "+");

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Error en la API Gutendex: " + response.code());
            }
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException("Fallo en la consulta: " + e.getMessage());
        }
    }
}

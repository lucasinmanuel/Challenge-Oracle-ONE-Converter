package com.converters.utils;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {
    public String getData(String url){
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null,"Ocorreu algum error.","Error",JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        return response.body();
    }

}

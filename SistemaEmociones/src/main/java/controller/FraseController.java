/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author juanc
 */
import java.io.*;
import java.net.*;

public class FraseController {

    public String obtenerFrase(String emocion) {
        try {
            // PASO 1: Obtener frase de ZenQuotes
            URL url = new URL("https://zenquotes.io/api/random");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "UTF-8")
            );
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }
            reader.close();

            String json = respuesta.toString();
            String textoIngles = json.split("\"q\":\"")[1].split("\"")[0];
            String autor       = json.split("\"a\":\"")[1].split("\"")[0];

            //  Traducir al español
            String textoEspanol = traducir(textoIngles);

            //  Decodificar caracteres especiales
            textoEspanol = decodificarUnicode(textoEspanol);

            return "\"" + textoEspanol + "\"\n\n— " + autor;

        } catch (Exception e) {
            return "\"Cada dia es una nueva oportunidad para ser mejor.\"\n\n— Anonimo";
        }
    }

    private String traducir(String texto) {
        try {
            String textoCodificado = URLEncoder.encode(texto, "UTF-8");
            URL url = new URL("https://api.mymemory.translated.net/get?q="
                    + textoCodificado + "&langpair=en|es");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "UTF-8")
            );
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }
            reader.close();

            String json = respuesta.toString();
            String traducido = json.split("\"translatedText\":\"")[1].split("\"")[0];
            return traducido;

        } catch (Exception e) {
            return texto;
        }
    }

    
    private String decodificarUnicode(String texto) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < texto.length()) {
            if (i + 5 <= texto.length()
                    && texto.charAt(i) == '\\'
                    && texto.charAt(i + 1) == 'u') {
                try {
                    String hex = texto.substring(i + 2, i + 6);
                    sb.append((char) Integer.parseInt(hex, 16));
                    i += 6;
                } catch (NumberFormatException e) {
                    sb.append(texto.charAt(i));
                    i++;
                }
            } else {
                sb.append(texto.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}
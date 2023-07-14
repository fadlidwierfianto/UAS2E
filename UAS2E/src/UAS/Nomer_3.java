package UAS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Nomer_3 {private static final String SERVER_URL = "https://dummyjson.com/products"; // Ganti dengan URL server yang sesuai
    private static final String ACCESS_ID = "1234567";
    private static final String USER_KEY = "faY738sH";

    public static void main(String[] args) {
        try {
            // Mendapatkan data JSON dari server
            String jsonData = fetchDataFromServer();

            // Parsing data JSON
            JSONObject json = new JSONObject(jsonData);
            JSONArray productsArray = json.getJSONArray("products");

            // Menginisialisasi array produk
            Product[] products = new Product[productsArray.length()];

            // Membaca data produk dari JSON
            for (int i = 0; i < productsArray.length(); i++) {
                JSONObject productObj = productsArray.getJSONObject(i);
                int id = productObj.getInt("id");
                String title = productObj.getString("title");
                String description = productObj.getString("description");
                int price = productObj.getInt("price");
                double discountPercentage = productObj.getDouble("discountPercentage");
                double rating = productObj.getDouble("rating");
                int stock = productObj.getInt("stock");
                String brand = productObj.getString("brand");
                String category = productObj.getString("category");
                String thumbnail = productObj.getString("thumbnail");
                JSONArray imagesArray = productObj.getJSONArray("images");
                String[] images = new String[imagesArray.length()];
                for (int j = 0; j < imagesArray.length(); j++) {
                    images[j] = imagesArray.getString(j);
                }
                products[i] = new Product(id, title, description, price, discountPercentage, rating, stock, brand, category, thumbnail, images);
            }

            // Menggunakan teknik Selection Sort untuk mengurutkan ratings secara ascending
            selectionSort(products);

            // Menampilkan data yang diurutkan
            System.out.println("Data Produk yang Diurutkan (Berdasarkan Rating Ascending):");
            for (Product product : products) {
                System.out.println(product);
                System.out.println("----------------------");
            }
        } catch (IOException e) {
            System.out.println("Gagal mengambil data dari server: " + e.getMessage());
        }
    }

    private static String fetchDataFromServer() throws IOException {
        URL url = new URL(SERVER_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Cons_ID", ACCESS_ID);
        connection.setRequestProperty("user_key", USER_KEY);

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } finally {
            connection.disconnect();
        }

        return response.toString();
    }

    private static void selectionSort(Product[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].getRating() < arr[minIndex].getRating()) {
                    minIndex = j;
                }
            }
            Product temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}

package com.example.android.booklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

    public static final String LOG_TAG = Utilities.class.getSimpleName();

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                jsonResponse = null;
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<BookListing> extractFeatureFromJson(String booksJSON) {

        if (booksJSON.isEmpty()) {
            return null;
        }

        List<BookListing> bookList = new ArrayList<BookListing>();

        try {
            JSONObject baseJsonResponse = new JSONObject(booksJSON);
            Integer totalItems = baseJsonResponse.getInt("totalItems");

            if (totalItems == 0) {
                return null;
            }

            JSONArray bookArray = baseJsonResponse.getJSONArray("items");

            if (bookArray.length() > 0) {

                for (int i = 0; i < bookArray.length(); i++) {

                    JSONObject book = bookArray.getJSONObject(i);
                    JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                    String title = volumeInfo.getString("title");
                    String publisher = "NO PUBLISHER";
                    try {
                        publisher = volumeInfo.getString("publisher");
                    } catch (JSONException e) {
                    }
                    String author = "NO AUTHOR";
                    try {
                        JSONArray authors = volumeInfo.getJSONArray("authors");
                        author = authors.getString(0);
                    } catch (JSONException e) {

                    }

                    JSONObject saleInfo = book.getJSONObject("saleInfo");
                    String saleability = saleInfo.getString("saleability");
                    String link;
                    if (saleability.equals("FOR_SALE")) {
                        link = saleInfo.getString("buyLink");
                    } else {
                        link = "NOT AVAILABLE";
                    }

                    bookList.add(new BookListing(title, author, publisher, link));
                }

                return bookList;
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the book JSON results", e);
        }
        return null;
    }
}

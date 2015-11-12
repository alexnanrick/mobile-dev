package com.zontzor.lab8_networking;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity
{
    private static final String DEBUG_TAG = "HttpExample";
    private EditText urlText;
    private TextView textView;
    private Button connectButton;
    private Button retrieveButton;

    DBManager db = new DBManager(this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlText = (EditText) findViewById(R.id.myUrl);
        textView = (TextView) findViewById(R.id.myText);
        connectButton = (Button) findViewById(R.id.button);
        retrieveButton = (Button) findViewById(R.id.retrieveButton);
        textView.setMovementMethod(new ScrollingMovementMethod());

        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Gets the URL from the UI's text field.
                String stringUrl = "http://jsonplaceholder.typicode.com/todos";

                // Check to see if a network connection is available
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    new DownloadWebpageTask().execute(stringUrl);
                } else {
                    textView.setText("No network connection available");
                }
            }
        });

        retrieveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = urlText.getText().toString();
                try {
                    db.open();
                    Cursor result = db.getTask(id);
                    String selection = "{" + "title:" + result.getString(1) + ", completed:" + result.getString(2) + "}";
                    textView.setText(selection);
                } catch (Exception ex) {
                    Context context = getApplicationContext();
                    CharSequence text = "Error opening database";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } finally {
                    db.close();
                }
            }
        });
    }

    // Uses AsyncTask to create a task away from the main UI thread. This task takes a
    // URL string and uses it to create an HttpUrlConnection. Once the connection
    // has been established, the AsyncTask downloads the contents of the webpage as
    // an InputStream. Finally, the InputStream is converted into a string, which is
    // displayed in the UI by the AsyncTask's onPostExecute method.
    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            String data ="";

            try {
                db.open();
            } catch (Exception ex) {
                Context context = getApplicationContext();
                CharSequence text = "Error opening database";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            try {
                JSONArray myArray = new JSONArray(result);

                for(int i=0; i < myArray.length(); i++) {
                    JSONObject jsonObject = myArray.getJSONObject(i);

                    String id = jsonObject.optString("id");
                    String title = jsonObject.optString("title");
                    String completed = jsonObject.optString("completed");

                    db.insertTask(id, title, completed);

                    data += "id= " + id + " \n " +
                            "Title= " + title + " \n " +
                            "Completed= " + completed + " \n ";

                    textView.setText("Data Inserted");
                }
            } catch (JSONException je) {
                textView.setText("JSON Parse Error");
            } finally {
                db.close();
            }
        }
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content as a InputStream, which it returns as
    // a string.
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 1000;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = convertStreamToString(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    // Reads an InputStream and converts it to a String
    private String convertStreamToString(InputStream is) {
        // Reads data from the input stream until the buffer is full
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        // Stores the data
        StringBuilder sb = new StringBuilder();
        // Temp var holds each line
        String line = null;
        try {
            // Read in data from the buffer until empty
            while ((line = reader.readLine()) != null) {
                // Add line to end of string then add escape char
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
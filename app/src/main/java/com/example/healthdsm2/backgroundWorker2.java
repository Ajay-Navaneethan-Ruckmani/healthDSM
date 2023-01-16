package com.example.healthdsm2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class backgroundWorker2 extends AsyncTask<String, Void, String> {
    Context context;
    // AlertDialog alertDialog;
    backgroundWorker2 (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        Log.e("doinbackground", "inside of doinbackground1" + params[0]);
        Log.e("doinbackground", "inside of doinbackground2" + params[1]);
        Log.e("doinbackground", "inside of doinbackground3" + params[2]);
        String type = params[0];
        //String login_url = "http://10.0.2.2/android_connect/send.php";

        //  String id;
        if (type.equals("receive")){
            String id = params[1];

            //    String get_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
            // String login_url = "http://10.144.127.124/android_connect/receive.php?id="+params[1]+"";
            String login_url = "https://kunet.kingston.ac.uk/ku41081/android_connect/receive.php?id="+params[1]+"";

            Log.e("doinbackground", "inside if of of doinbackground");
            try {
                Log.e("doinbackground", "inside try of doinbackground" + params[1]);
                //String id = params[1];
                //String Measurement = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                // httpURLConnection.setRequestMethod("POST");
                // httpURLConnection.setDoOutput(true);
                // httpURLConnection.setDoInput(true);
                // OutputStream outputStream = httpURLConnection.getOutputStream();
                // BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //    String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                //+"&"
//                        +URLEncoder.encode("Measurement","UTF-8")+"="+URLEncoder.encode(Measurement,"UTF-8");
                //   bufferedWriter.write(post_data);
                //   bufferedWriter.flush();
                //  bufferedWriter.close();
                //   outputStream.close();
                // String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                //  bufferedWriter.write(post_data);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                Log.e("doinbackground", "after while loop" + result);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        //   alertDialog = new AlertDialog.Builder(context).create();

        // alertDialog.setTitle("Login Status");
        Log.e("onPreExecute", "inside of onPreExecute");
    }

    //@Override
    // protected void onPostExecute(String result) {

    //     Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    //     try{
    //         JSONObject jsonResult = new JSONObject(result);
    //         int success = jsonResult.getInt("success");
    //         if (success == 1) {
    //             Toast.makeText(context.getApplicationContext(), "Ok there is data in measurement", Toast.LENGTH_SHORT).show();
    //             JSONArray measurs = jsonResult.getJSONArray("measurs");
    //             for(int i=0; i < measurs.length(); i++){
    //                 JSONObject measur = measurs.getJSONObject(i);
    //                 int id = measur.getInt("id");
    //                 String userid = measur.getString("userid");
    //                 double measurement = measur.getDouble("measur");
    //                 String datetime = measur.getString("datetime");
    //                 String line = id + "|" + userid + "|" + measurement + "|" + datetime;
    //                 adapter.add(line);


    //             }

    //         }
    //         else {
    //             Toast.makeText(context.getApplicationContext(), "not Ok there is no data in measurement", Toast.LENGTH_SHORT).show();

    //         }

    //     }

    //     catch (JSONException e) {
    //         e.printStackTrace();
    //     }

    //     alertDialog.setMessage(result);
    //     alertDialog.show();

    //}

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


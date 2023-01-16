package com.example.healthdsm2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class History extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = (ListView)findViewById(R.id.LVHistory);
        adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        String type = "receive";
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        String id = mPreferences.getString("key", "");

        Log.e("OnSend", "inside of OnSend" + type + id);
        @SuppressLint("StaticFieldLeak") backgroundWorker2 BackgroundWorker2 = new backgroundWorker2(this){

            protected void onPostExecute(String result) {
                //Do your thing
                Toast.makeText(context.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                Log.e("OnSend", "inside of postexecute....");
                try{
                    Log.e("OnSend", "inside of postexecute try" + result);
                    JSONObject jsonResult = new JSONObject(result);
                    Log.e("OnSend", "inside of postexecute try");
                    int success = jsonResult.getInt("success");

                    if (success == 1) {
                        Toast.makeText(context.getApplicationContext(), "Ok there is data in measurement", Toast.LENGTH_SHORT).show();
                        JSONArray measurs = jsonResult.getJSONArray("measurement");
                        for(int i=0; i < measurs.length(); i++){
                            JSONObject measur = measurs.getJSONObject(i);
                            int id = measur.getInt("id");
                            String userid = measur.getString("userid");
                            double measurement = measur.getDouble("measur");
                            String datetime = measur.getString("datetime");
                            String line = id + "|" + userid + "|" + measurement + "|" + datetime;
                            adapter.add(line);


                        }

                    }
                    else {
                        Toast.makeText(context.getApplicationContext(), "not Ok there is no data in measurement", Toast.LENGTH_SHORT).show();

                    }

                }

                catch (JSONException e) {
                    e.printStackTrace();
                }

                //     alertDialog.setMessage(result);
                //     alertDialog.show();
            }

        };


        BackgroundWorker2.execute(type, id, id);

    }
}

package com.codebusters.medlife;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class Record extends AppCompatActivity {

    TextView ed2,ed3;
    EditText ed1;
    Button btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
                ed1=(EditText)findViewById(R.id.editText);
                ed2=(TextView) findViewById(R.id.editText2);
                ed3=(TextView) findViewById(R.id.editText3);
                btn=(Button)findViewById(R.id.button);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name=ed1.getText().toString();
                        new Medicine().execute("https://api.fda.gov/drug/event.json?api_key=eMdtaE53XdHcAnSK2lBu0awJGiPLQ3QRjDUCDhxL&search="+name);
                        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    }
                });
            }

            class Medicine extends AsyncTask<String,String,String> {
                @Override
                protected String doInBackground(String... params) {
                    HttpURLConnection con=null;
                    BufferedReader reader=null;
                    String reac="REACTIONS - ";
                    String drugsname="DRUGS - ";
                    URL url= null;
                    try {
                        url = new URL(params[0]);
                        con = (HttpURLConnection)url.openConnection();
                        con.setRequestMethod("GET");
                        con.setDoInput(true);
                        con.connect();
                        InputStream stram = con.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(stram));
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                        }
                        String jsonStr = buffer.toString();
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        JSONArray parentArray = jsonObj.getJSONArray("results");
                        StringBuffer readData = new StringBuffer();
                        JSONObject finalObject = parentArray.getJSONObject(0);
                        JSONObject paitent = finalObject.getJSONObject("patient");
                        JSONArray reaction = paitent.getJSONArray("reaction");
                        for (int i = 0; i < reaction.length()&&i<4; i++){
                            JSONObject finalObject2 = reaction.getJSONObject(i);
                            String a = finalObject2.getString("reactionmeddrapt");
                            reac=reac+a+"\n\r";
                        }
                        JSONArray drugs = paitent.getJSONArray("drug");
                        for (int i = 0; i <1; i++) {
                            JSONObject finalObject7 = drugs.getJSONObject(i);
                            String a = finalObject7.getString("medicinalproduct");
                            drugsname = drugsname + a+"\n";
                        }

                        final String finalReac = reac;
                        final String finalDrugsname = drugsname;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ed2.setText(finalReac);
                                ed3.setText(finalDrugsname);
                            }
                        });



                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }
        }


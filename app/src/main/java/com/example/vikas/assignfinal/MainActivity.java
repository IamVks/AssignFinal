package com.example.vikas.assignfinal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String url = "https://test-api.nevaventures.com/";
    private List<DataSet> list = new ArrayList<DataSet>();
    private ListView listView;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = (ListView) findViewById(R.id.list);
        adapter = new Adapter(this, list);
        listView.setAdapter(adapter);

        JsonObjectRequest jsn = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0 ; i < jsonArray.length(); i++){
                                JSONObject emp = jsonArray.getJSONObject(i);
                                DataSet dataSet = new DataSet();

                                if (emp.has("id")){
                                    dataSet.setId(emp.getInt("id"));
                                }
                                else{
                                    dataSet.setId(100);
                                }
                                if (emp.has("name")){
                                    dataSet.setName(emp.getString("name"));
                                }
                                else {
                                    dataSet.setName("Deault Name");
                                }
                                if (emp.has("image")){
                                    dataSet.setImage(emp.getString("image"));
                                }
                                else {
                                    dataSet.setImage("");
                                }
                                if (emp.has("skills")){
                                    dataSet.setSkills(emp.getString("skills"));
                                }
                                else {
                                    dataSet.setSkills("Default Skills");
                                }
                                list.add(dataSet);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        Controller.getPermission().addToRequestQueue(jsn);
    }

}
package com.khair.home_work_2584;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;

    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.ListView);
        progressBar=findViewById(R.id.ProgressBar);

        String url="https://dummyjson.com/todos";

        progressBar.setVisibility(View.VISIBLE);

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                    progressBar.setVisibility(View.GONE);

                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("todos");
                    for (int x=0;x<jsonArray.length();x++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(x);
                        String todos=jsonObject1.getString("todo");
                        String userId=jsonObject1.getString("userId");
                        Boolean completed=jsonObject1.getBoolean("completed");

                        hashMap=new HashMap<>();
                        hashMap.put("todo",todos);
                        hashMap.put("userId",userId);
                        hashMap.put("completed",completed.toString());
                        arrayList.add(hashMap);


                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                MyAdapter myAdapter=new MyAdapter();
                listView.setAdapter(myAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.GONE);

            }
        });

        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        queue.add(objectRequest);

    }
 //************************************************************************************************
 public class MyAdapter extends BaseAdapter{

     @Override
     public int getCount() {
         return arrayList.size();
     }

     @Override
     public Object getItem(int position) {
         return null;
     }

     @Override
     public long getItemId(int position) {
         return 0;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {

         LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View myView=inflater.inflate(R.layout.item,parent,false);

         TextView userId=myView.findViewById(R.id.textView3);
         TextView title=myView.findViewById(R.id.textView2);
         TextView completed=myView.findViewById(R.id.textView4);
         ImageView imageView=myView.findViewById(R.id.imageView);

       HashMap<String,String>hashMap=arrayList.get(position);
       String userId1=hashMap.get("userId");
       String title1=hashMap.get("todo");
       String completed1=hashMap.get("completed");

       userId.append(userId1);
       title.setText(title1);



       if (completed1.equals("true")){
           imageView.setImageResource(R.drawable.img_1);
       }else {
           imageView.setImageResource(R.drawable.img_2);

       }







         return myView;
     }
 }





 //+++9999999999999999999999999999+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
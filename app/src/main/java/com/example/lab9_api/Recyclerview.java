package com.example.lab9_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RecyclervAdapter extends RecyclerView.Adapter<RecyclervAdapter.StudentViewHolder> {
    private ArrayList<Student> arrayList;
    Context context;
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = arrayList.get(position);
        holder.student= student;
        holder.txtId.setText(student.getId());
        holder.txtName.setText(student.getName());
        holder.txtClass.setText(student.getClass1());
        holder.txtEmail.setText(student.getEmail());

    }

    public RecyclervAdapter(ArrayList<Student> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public String url = "https://60ade95c80a61f0017331f03.mockapi.io/student";

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView txtId ;
        TextView txtName ;
        TextView txtClass ;
        TextView txtEmail ;
        Button buttonDelete;
        Student student;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            txtId=itemView.findViewById(R.id.txtId);
            txtClass=itemView.findViewById(R.id.txtClass);
            txtEmail=itemView.findViewById(R.id.txtEmail);
            buttonDelete = itemView.findViewById(R.id.btnDelete);
            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   DeleteApi(url,student);

                }
            });

        }



    }
    public void setList(ArrayList<Student> studentArrayList) {
        studentArrayList = studentArrayList;
        notifyDataSetChanged();
    }
    private void DeleteApi(String url, Student user){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + user.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
                List<Student> listTemp = new ArrayList<Student>(arrayList);
                listTemp.remove(user);
                arrayList.clear();
                arrayList.addAll(listTemp);
                notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }



}

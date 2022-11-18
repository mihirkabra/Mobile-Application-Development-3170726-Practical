package com.example.practical11;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Student> studentsList;

    public RecyclerAdapter(Context context, ArrayList<Student> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.name.setText(studentsList.get(position).getName());
        holder.city.setText(studentsList.get(position).getCity());
        holder.edit.setOnClickListener(view -> {
            Student student = studentsList.get(position);
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("name", student.getName());
            intent.putExtra("city", student.getCity());
            intent.putExtra("id", student.getDocID());
            context.startActivity(intent);
        });
        holder.delete.setOnClickListener(view -> {
            FirebaseFirestore.getInstance().collection("students").document(studentsList.get(position).getDocID()).delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Data Deleted!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> Toast.makeText(context, "Error while deleting the data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            read();
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, city;
        ImageButton edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            city = itemView.findViewById(R.id.address);
            edit = itemView.findViewById(R.id.button_edit);
            delete = itemView.findViewById(R.id.button_delete);
        }
    }

    public void read() {
        studentsList.clear();
        notifyDataSetChanged();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("students").addSnapshotListener((documentSnapshots, e) -> {
            for (DocumentSnapshot doc : documentSnapshots) {
                studentsList.add(new Student(
                        doc.getString("name"),
                        doc.getString("city"),
                        doc.getId()
                ));
            }
        });
        notifyDataSetChanged();
    }
}

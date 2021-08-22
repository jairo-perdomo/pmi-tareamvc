package com.example.tareamvc.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tareamvc.R;
import com.example.tareamvc.ViewActicity;
import com.example.tareamvc.entity.Employee;

import java.util.ArrayList;

public class ListEmployeesAdapter extends RecyclerView.Adapter<ListEmployeesAdapter.EmployeeViewHolder> {
    ArrayList<Employee> listEmployees;

    public ListEmployeesAdapter(ArrayList<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_empleado, null, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListEmployeesAdapter.EmployeeViewHolder holder, int position) {
        holder.viewNombre.setText(listEmployees.get(position).getName());
        holder.viewApellidos.setText(listEmployees.get(position).getLastNames());
        holder.viewEdad.setText(listEmployees.get(position).getAge());
        holder.viewDireccion.setText(listEmployees.get(position).getAddress());
        holder.viewPuestos.setText(listEmployees.get(position).getPosition());
    }

    @Override
    public int getItemCount() {
        return listEmployees.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellidos, viewEdad, viewDireccion, viewPuestos;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellidos = itemView.findViewById(R.id.viewApellidos);
            viewEdad = itemView.findViewById(R.id.viewEdad);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewPuestos = itemView.findViewById(R.id.viewPuestos);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ViewActicity.class);
                    intent.putExtra("ID", listEmployees.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

}

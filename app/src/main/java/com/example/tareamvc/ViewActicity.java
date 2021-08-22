package com.example.tareamvc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tareamvc.database.DbEmployees;
import com.example.tareamvc.entity.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewActicity extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtEdad, txtDireccion, txtPuestos;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    
    Employee employee;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_acticity);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtEdad = findViewById(R.id.txtEdad);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtPuestos = findViewById(R.id.txtPuestos);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbEmployees dbEmployees = new DbEmployees(ViewActicity.this);
        employee = dbEmployees.showEmployee(id);

        if(employee != null){
            txtNombre.setText(employee.getName());
            txtApellidos.setText(employee.getLastNames());
            txtEdad.setText(employee.getAge());
            txtDireccion.setText(employee.getAddress());
            txtPuestos.setText(employee.getPosition());
            btnGuarda.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtApellidos.setInputType(InputType.TYPE_NULL);
            txtEdad.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtPuestos.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewActicity.this, UpdateActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewActicity.this);
                builder.setMessage("¿Desea eliminar este registro?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbEmployees.deleteEmployee(id)){
                                    list();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    private void list(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
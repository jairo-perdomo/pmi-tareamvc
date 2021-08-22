package com.example.tareamvc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tareamvc.database.DbEmployees;
import com.example.tareamvc.entity.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateActivity extends AppCompatActivity {

    EditText txtNombre, txtApellidos, txtEdad, txtDireccion, txtPuestos;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Employee employee;
    int id = 0;

    @SuppressLint("RestrictedApi")
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
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbEmployees dbEmployees = new DbEmployees(UpdateActivity.this);
        employee = dbEmployees.showEmployee(id);

        if (employee != null) {
            txtNombre.setText(employee.getName());
            txtApellidos.setText(employee.getLastNames());
            txtEdad.setText(employee.getAge());
            txtDireccion.setText(employee.getAddress());
            txtPuestos.setText(employee.getPosition());

        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtApellidos.getText().toString().equals("")) {
                    correcto = dbEmployees.updateEmployee(id, txtNombre.getText().toString(), txtApellidos.getText().toString(), txtEdad.getText().toString(), txtDireccion.getText().toString(), txtPuestos.getText().toString());

                    if(correcto){
                        Toast.makeText(UpdateActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(UpdateActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(UpdateActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, ViewActicity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
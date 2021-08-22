package com.example.tareamvc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tareamvc.adapters.ListEmployeesAdapter;
import com.example.tareamvc.database.DbEmployees;
import com.example.tareamvc.entity.Employee;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaEmpleados;
    ArrayList<Employee> listaArrayEmpleados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEmpleados = findViewById(R.id.listaEmpleados);
        listaEmpleados.setLayoutManager(new LinearLayoutManager(this));

        DbEmployees dbEmployees = new DbEmployees(MainActivity.this);

        listaArrayEmpleados = new ArrayList<>();

        ListEmployeesAdapter adapter = new ListEmployeesAdapter(dbEmployees.showEmployees());
        listaEmpleados.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

}
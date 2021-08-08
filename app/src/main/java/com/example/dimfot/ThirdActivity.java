package com.example.dimfot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private DBHelper dh;
    ListView listView;

    Button btLimpar, btAdd;

    private ArrayList<String> registros = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btLimpar = (Button) findViewById(R.id.btLimpar);
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ThirdActivity.this);
                adb.setTitle("Atenção!");
                adb.setMessage("Tem certeza que deseja excluir todos os dados?");
                adb.setNegativeButton("Não", null);
                adb.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dh.deleteAll();
                        finish();
                        startActivity(getIntent());
                    }
                });
                adb.show();

            }
        });

        btAdd = (Button) findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(ThirdActivity.this , SecondActivity.class);
                startActivity(it);
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.listview);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registros);
        listView.setAdapter(adapter);

        dh = new DBHelper(this);
        List<Projeto> projetos = dh.queryGetAll();

        if (projetos == null) {
            AlertDialog.Builder adb = new AlertDialog.Builder(ThirdActivity.this);
            adb.setTitle("Atenção");
            adb.setMessage("Não há registros cadastrados");
            adb.show();
            return;
        } else {
            for (int i = 0; i < projetos.size(); i++) {
                //Projeto projeto = (Projeto) projetos.get(i);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, projetos);
                //System.out.println(listAdapter);
                listView.setAdapter(listAdapter);
                adapter.notifyDataSetChanged();
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> proj, View view, int position, long id) {
                Projeto projeto = (Projeto) projetos.get((int) id);
                AlertDialog.Builder adb = new AlertDialog.Builder(ThirdActivity.this);
                String x = projeto.getNomeProjeto();
                        //String.valueOf(id);
                adb.setTitle("Registro " + id + ": " + ""+ projeto.getNomeProjeto());
                adb.setMessage("Data de Início: " + projeto.getDataInicio() +
                        "\nData de Término: " + projeto.getDataTermino() +
                        "\nNomde do Cliente: " + projeto.getNomeCliente() +
                        "\nEndereço: " + projeto.getEndereco() +
                        "\nBairro: " + projeto.getBairro() +
                        "\nCEP: " + projeto.getCep() +
                        "\nCidade: " + projeto.getCidade() +
                        "\nPaís: " + projeto.getPais() +
                        "\nLatitude: " + projeto.getLatitude() +
                        "\nLongitude: " + projeto.getLongitude() +
                        "\nResponsável: " + projeto.getResponsavel() +
                        "\nPotência da placa [W]: " + projeto.getPotPlaca() +
                        "\nQuantidade de placas [un.]: " + projeto.getQtdPlaca() +
                        "\nPotência total gerada [W]: " + projeto.getPotGerada() +
                        "\nPotência mínima do inversor [kW]: " + projeto.getPotInvMin() +
                        "\nPotência máxima do inversor [kW]: " + projeto.getPotInvMax());
                adb.setPositiveButton("Adicionar outro projeto",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                Intent it = new Intent();
                                it.setClass(ThirdActivity.this , SecondActivity.class);
                                startActivity(it);
                                finish();
                            }
                        });

                adb.setNegativeButton("Excluir",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                //String idS = String.valueOf(id);
                                dh.removeS(x);
                                adapter.notifyDataSetChanged();
                                finish();
                                startActivity(getIntent());
                            }
                        });
                adb.create().show();

            }
        });

    }

}
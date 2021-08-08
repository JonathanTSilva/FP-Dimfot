package com.example.dimfot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.sql.ParameterMetaData;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    private DBHelper dh;

    Button btDataInicio, btDataTermino, btLocalizacao, btCalcular, btVoltar, btEnviar, btListar;
    EditText etNomeProjeto, etNomeCliente, etResponsavel, etEndereco, etBairro, etCEP, etCidade,
            etPais, etLatitude, etLongitude, etCustoEq, etConsumoMes, etConusumoDia, etHSPmes,
            etHSPdia, etRendimento, etPotPlaca;
    TextView tvDataInicio, tvDataTermino, tvPotPlaca, tvQtdPlaca, tvPotGerada, tvPotInvMin, tvPotInvMax;


    static final int DATE_DIALOG_ID1 = 0;
    static final int DATE_DIALOG_ID2 = 1;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.dh = new DBHelper(this);

        btDataInicio = (Button) findViewById(R.id.btDataInicio);
        btDataTermino = (Button) findViewById(R.id.btDataTermino);
        btLocalizacao = (Button) findViewById(R.id.btLocalizacao);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        btVoltar = (Button) findViewById(R.id.btVoltar1);
        btEnviar = (Button) findViewById(R.id.btEnviar);
        btListar = (Button) findViewById(R.id.btListar);

        etNomeProjeto = (EditText) findViewById(R.id.etNomeProjeto);
        etNomeCliente = (EditText) findViewById(R.id.etNomeCliente);
        etResponsavel = (EditText) findViewById(R.id.etResponsavel);
        etEndereco = (EditText) findViewById(R.id.etEndereco);
        etBairro = (EditText) findViewById(R.id.etBairro);
        etCEP = (EditText) findViewById(R.id.etCEP);
        etCidade = (EditText) findViewById(R.id.etCidade);
        etPais = (EditText) findViewById(R.id.etPais);
        etLatitude = (EditText) findViewById(R.id.etLatitude);
        etLongitude = (EditText) findViewById(R.id.etLongitude);
        etCustoEq = (EditText) findViewById(R.id.etCustoEq);
        etConsumoMes = (EditText) findViewById(R.id.etConsumoMes);
        etConusumoDia = (EditText) findViewById(R.id.etConsumoDia);
        etHSPmes = (EditText) findViewById(R.id.etHSPmes);
        etHSPdia = (EditText) findViewById(R.id.etHSPdia);
        etRendimento = (EditText) findViewById(R.id.etRendimento);
        etPotPlaca = (EditText) findViewById(R.id.etPotPlaca);

        tvDataInicio = (TextView) findViewById(R.id.tvDataInicio);
        tvDataTermino = (TextView) findViewById(R.id.tvDataTermino);
        tvPotPlaca = (TextView) findViewById(R.id.tvPotPlaca);
        tvQtdPlaca = (TextView) findViewById(R.id.tvQtdPlaca);
        tvPotGerada = (TextView) findViewById(R.id.tvPotGerada);
        tvPotInvMin = (TextView) findViewById(R.id.tvPotInvMin);
        tvPotInvMax = (TextView) findViewById(R.id.tvPotInvMax);

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent();

                it.setClass(SecondActivity.this, MainActivity.class);
                startActivity(it);
                finish();

            }
        });

        btDataInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btDataInicio)
                    showDialog(DATE_DIALOG_ID1);
            }
        });

        btDataTermino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btDataTermino)
                    showDialog(DATE_DIALOG_ID2);
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checar permissão:
                if (ActivityCompat.checkSelfPermission(SecondActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(SecondActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Potência Total dos Painéis:
                    float ConsumoDia, CustoEq, HSPmes, Rendimento, result1;

                    ConsumoDia = Float.parseFloat(etConusumoDia.getText().toString());
                    CustoEq = Float.parseFloat(etCustoEq.getText().toString());
                    HSPmes = Float.parseFloat(etHSPmes.getText().toString());
                    Rendimento = Float.parseFloat(etRendimento.getText().toString());

                    result1 = (ConsumoDia - (CustoEq/360))/(HSPmes*Rendimento/100);

                    String strRes1;
                    strRes1 = String.valueOf(result1);

                    tvPotPlaca.setText(strRes1);

                    //Quantidade de Placas:
                    float PotPlaca, result2;

                    PotPlaca = Float.parseFloat(etPotPlaca.getText().toString());

                    result2 = (result1*1000)/PotPlaca;

                    String strRes2;
                    strRes2 = String.valueOf((int) Math.ceil(result2));

                    tvQtdPlaca.setText(strRes2);

                    //Potência Total Gerada:
                    float result3;

                    result3 = PotPlaca*result2;

                    String strRes3;
                    strRes3 = String.valueOf(result3);

                    tvPotGerada.setText(strRes3);

                    //Potência do Inversor:
                    // Mínima:
                    float result4;

                    result4 = (float) ((result3*0.8)/1000);

                    String strRes4;
                    strRes4 = String.valueOf(result4);

                    tvPotInvMin.setText(strRes4);

                    // Máxima:
                    float result5;

                    result5 = (float) ((result3*1.2)/1000);

                    String strRes5;
                    strRes5 = String.valueOf(result5);

                    tvPotInvMax.setText(strRes5);

                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setMessage("Calculado com sucesso!");
                    adb.show();

                } catch (final NumberFormatException e) {
                    Toast.makeText(getApplicationContext(),"Cálculo inválido. Por favor, tente novamente.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNomeProjeto.getText().length() > 0 &&
                        tvDataInicio.getText().length() > 0 &&
                        tvDataTermino.getText().length() > 0 &&
                        etNomeCliente.getText().length() > 0 &&
                        etEndereco.getText().length() > 0 &&
                        etBairro.getText().length() > 0 &&
                        etCEP.getText().length() > 0 &&
                        etCidade.getText().length() > 0 &&
                        etPais.getText().length() > 0 &&
                        etLatitude.getText().length() > 0 &&
                        etLongitude.getText().length() > 0 &&
                        etResponsavel.getText().length() > 0 &&
                        etCustoEq.getText().length() > 0 &&
                        etConsumoMes.getText().length() > 0 &&
                        etConusumoDia.getText().length() > 0 &&
                        etHSPmes.getText().length() > 0 &&
                        etHSPdia.getText().length() > 0 &&
                        etRendimento.getText().length() > 0 &&
                        etPotPlaca.getText().length() > 0 &&
                        tvPotPlaca.getText().length() > 0 &&
                        tvQtdPlaca.getText().length() > 0 &&
                        tvPotGerada.getText().length() > 0 &&
                        tvPotInvMin.getText().length() > 0 &&
                        tvPotInvMax.getText().length() > 0){
                    dh.insert(etNomeProjeto.getText().toString(),
                            tvDataInicio.getText().toString(),
                            tvDataTermino.getText().toString(),
                            etNomeCliente.getText().toString(),
                            etEndereco.getText().toString(),
                            etBairro.getText().toString(),
                            etCEP.getText().toString(),
                            etCidade.getText().toString(),
                            etPais.getText().toString(),
                            etLatitude.getText().toString(),
                            etLongitude.getText().toString(),
                            etResponsavel.getText().toString(),
                            etCustoEq.getText().toString(),
                            etConsumoMes.getText().toString(),
                            etConusumoDia.getText().toString(),
                            etHSPmes.getText().toString(),
                            etHSPdia.getText().toString(),
                            etRendimento.getText().toString(),
                            etPotPlaca.getText().toString(),
                            tvPotPlaca.getText().toString(),
                            tvQtdPlaca.getText().toString(),
                            tvPotGerada.getText().toString(),
                            tvPotInvMin.getText().toString(),
                            tvPotInvMax.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("Cadastro Realizado!");
                    adb.show();

                    etNomeProjeto.setText("");
                    tvDataInicio.setText("");
                    tvDataTermino.setText("");
                    etNomeCliente.setText("");
                    etEndereco.setText("");
                    etBairro.setText("");
                    etCEP.setText("");
                    etCidade.setText("");
                    etPais.setText("");
                    etLatitude.setText("");
                    etLongitude.setText("");
                    etResponsavel.setText("");
                    etCustoEq.setText("");
                    etConsumoMes.setText("");
                    etConusumoDia.setText("");
                    etHSPmes.setText("");
                    etHSPdia.setText("");
                    etRendimento.setText("");
                    etPotPlaca.setText("");
                    tvPotPlaca.setText("");
                    tvQtdPlaca.setText("");
                    tvPotGerada.setText("");
                    tvPotInvMin.setText("");
                    tvPotInvMax.setText("");
                }
                else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Atenção");
                    adb.setMessage("Todos os campos devem ser preenchidos!");
                    adb.show();
                }
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();

                it.setClass(SecondActivity.this , ThirdActivity.class);
                startActivity(it);
                finish();

                /*List<Projeto> projetos = dh.queryGetAll();
                if (projetos == null){
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Atenção");
                    adb.setMessage("Não há registros cadastrados");
                    adb.show();
                    return;
                }
                for (int i=0; i<projetos.size(); i++){
                    Projeto projeto = (Projeto) projetos.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Registro "+i);
                    adb.setMessage("Nome do Projeto: " + projeto.getNomeProjeto() +
                            "\nData de Início: " + projeto.getDataInicio() +
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
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    //adb.show();
                }*/
            }
        });

    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;

        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener( new OnCompleteListener<Location>() {
            private Task<Location> task;

            @Override
            public void onComplete(@NonNull Task<Location> task) {
                this.task = task;
                Location location = task.getResult();

                etEndereco.setText("adresses.get(0).getAddressLine(0)");

                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(SecondActivity.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );

                        etEndereco.setText(Html.fromHtml("" + addresses.get(0).getThoroughfare() + ", " + addresses.get(0).getFeatureName()));
                        etBairro.setText(Html.fromHtml("" + addresses.get(0).getSubLocality()));
                        etCEP.setText(Html.fromHtml("" + addresses.get(0).getPostalCode()));
                        etCidade.setText(Html.fromHtml("" + addresses.get(0).getSubAdminArea()));
                        etPais.setText(Html.fromHtml("" + addresses.get(0).getCountryName()));
                        etLatitude.setText(Html.fromHtml("" + String.valueOf(addresses.get(0).getLatitude())));
                        etLongitude.setText(Html.fromHtml("" + String.valueOf(addresses.get(0).getLongitude())));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Erro");
                    adb.setMessage("Sinal de GPS não encontrado!");
                    adb.show();
                }
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DATE_DIALOG_ID1:
                return new DatePickerDialog(this, mDateSetListener1, ano, mes, dia);
            case DATE_DIALOG_ID2:
                return new DatePickerDialog(this, mDateSetListener2, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener1 =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String data1 = String.valueOf(dayOfMonth) + " / " + String.valueOf(monthOfYear+1) + " / " + String.valueOf(year);
                    tvDataInicio.setText(data1);
                }
            };

    private DatePickerDialog.OnDateSetListener mDateSetListener2 =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String data2 = String.valueOf(dayOfMonth) + " / " + String.valueOf(monthOfYear+1) + " / " + String.valueOf(year);
                    tvDataTermino.setText(data2);
                }
            };

}
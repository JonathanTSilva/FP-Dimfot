package com.example.dimfot;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private static final String DATABASE_NAME = "dimfot.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "projeto";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStmt;
    private static final String insert = "insert into " + TABLE_NAME + " (nomeProjeto, dataInicio, dataTermino, nomeCliente, endereco, bairro, cep, cidade, pais, latitude, longitude, responsavel, custoEq, consumoMes, consumoDia, hspMes, hspDia, rendimento, potPlaca, potTotPlaca, qtdPlaca, potGerada, potInvMin, potInvMax) " + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public DBHelper(Context context){
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(insert);
    }

    public long insert (String nomeProjeto, String dataInicio, String dataTermino, String nomeCliente,
                        String endereco, String bairro, String cep, String cidade, String pais,
                        String latitude, String longitude, String responsavel, String custoEq,
                        String consumoMes, String consumoDia, String hspMes, String hspDia,
                        String rendimento, String potPlaca, String potTotPlaca, String qtdPlaca,
                        String potGerada, String potInvMin, String potInvMax){
        this.insertStmt.bindString(1, nomeProjeto);
        this.insertStmt.bindString(2, dataInicio);
        this.insertStmt.bindString(3, dataTermino);
        this.insertStmt.bindString(4, nomeCliente);
        this.insertStmt.bindString(5, endereco);
        this.insertStmt.bindString(6, bairro);
        this.insertStmt.bindString(7, cep);
        this.insertStmt.bindString(8, cidade);
        this.insertStmt.bindString(9, pais);
        this.insertStmt.bindString(10, latitude);
        this.insertStmt.bindString(11, longitude);
        this.insertStmt.bindString(12, responsavel);
        this.insertStmt.bindString(13, custoEq);
        this.insertStmt.bindString(14, consumoMes);
        this.insertStmt.bindString(15, consumoDia);
        this.insertStmt.bindString(16, hspMes);
        this.insertStmt.bindString(17, hspDia);
        this.insertStmt.bindString(18, rendimento);
        this.insertStmt.bindString(19, potPlaca);
        this.insertStmt.bindString(20, potTotPlaca);
        this.insertStmt.bindString(21, qtdPlaca);
        this.insertStmt.bindString(22, potGerada);
        this.insertStmt.bindString(23, potInvMin);
        this.insertStmt.bindString(24, potInvMax);

        return this.insertStmt.executeInsert();
    }

    public void removeS(String x){
        this.db.execSQL("delete from " + TABLE_NAME + " where nomeProjeto='"+ x +"'");
        db.close();
    }

    public void deleteAll(){
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<Projeto> queryGetAll(){
        List<Projeto> list = new ArrayList<Projeto>();
        //try catch para que não trave o sistema, com tratamento do erro
        try {
            Cursor cursor = this.db.query(TABLE_NAME, new String[]{"nomeProjeto", "dataInicio", "dataTermino", "nomeCliente", "endereco", "bairro", "cep", "cidade", "pais", "latitude", "longitude", "responsavel", "custoEq", "consumoMes", "consumoDia", "hspMes", "hspDia", "rendimento", "potPlaca", "potTotPlaca", "qtdPlaca", "potGerada", "potInvMin", "potInvMax"},
                    null, null ,null, null, null, null);

            int nregistros = cursor.getCount();

            if (nregistros != 0){
                cursor.moveToFirst();
                do{
                    Projeto projeto = new Projeto(cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10),
                            cursor.getString(11),
                            cursor.getString(12),
                            cursor.getString(13),
                            cursor.getString(14),
                            cursor.getString(15),
                            cursor.getString(16),
                            cursor.getString(17),
                            cursor.getString(18),
                            cursor.getString(19),
                            cursor.getString(20),
                            cursor.getString(21),
                            cursor.getString(22),
                            cursor.getString(23));
                    list.add(projeto);
                } while (cursor.moveToNext());

                if (cursor != null & !cursor.isClosed()){
                    cursor.close();
                }

                return list;
            }
            else {
                return null;
            }
        } catch (Exception ex){
            return null;
        }
    }

    private  static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper (Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate (SQLiteDatabase db){
            String sql = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, nomeProjeto TEXT, dataInicio TEXT, dataTermino TEXT, nomeCliente TEXT, endereco TEXT, bairro TEXT, cep TEXT, cidade TEXT, pais TEXT, latitude TEXT, longitude TEXT, responsavel TEXT, custoEq TEXT, consumoMes TEXT, consumoDia TEXT, hspMes TEXT, hspDia TEXT, rendimento TEXT, potPlaca TEXT, potTotPlaca TEXT, qtdPlaca TEXT, potGerada TEXT, potInvMin TEXT, potInvMax TEXT);";
            db.execSQL(sql);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
            onCreate(db);
        }
    }

}

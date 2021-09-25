package com.example.realtime;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TableDynamic {

    private TableLayout tableLayout;
    private Context context;
    private String[] header;
    private ArrayList<String[]>data;
    private TableRow tableRow;
    private TextView txtCell;
    private int indexC;
    private int indexR;

    public TableDynamic(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public  void  addHeader(String[] header){
        this.header=header;
        createHeader();

    }

    public void addData(ArrayList<String[]>data,int conta){
        this.data=data;
        createDataTable(conta);
    }

    public void newRow(){
        tableRow=new TableRow(context);
    }

    private void newCell(){
        txtCell=new TextView(context);
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(25);
    }

    private  void createHeader(){
        indexC=0;
        newRow();
        while (indexC<header.length){
            newCell();
            txtCell.setText(header[indexC++]);
            txtCell.setBackgroundColor(Color.BLUE);
            txtCell.setTextColor(Color.YELLOW);
            //txtCell.setPadding(20,0,20,0);
            tableRow.addView(txtCell,newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable(int conta){
        String info, latitud="",longitud="";

        for (indexR=1;indexR<=conta;indexR++){
            newRow();
            for (indexC=0;indexC<=header.length;indexC++){
                newCell();
                String[] rows=data.get(indexR-1);
                info=(indexC<rows.length)?rows[indexC]:" ";
                txtCell.setText(info);
                txtCell.setBackgroundColor(Color.CYAN);
                txtCell.setTextColor(Color.BLACK);
                //txtCell.setLineSpacing(2.0f,3.0f);


                //info+="";
                //Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
                if(indexC==0){
                    txtCell.setPadding(20,0,50,0);
                }
                if(indexC==1){
                    txtCell.setPadding(30,0,50,0);

                }
                if(indexC==2){
                    txtCell.setPadding(30,0,50,0);

                }
                if(indexC==3){
                    txtCell.setPadding(30,0,50,0);

                }
               if((indexR!=1)&&(indexC==1)&&(info!=null)){

                     //try {
                         if(!info.equalsIgnoreCase(latitud)){
                             txtCell.setBackgroundColor(Color.RED);
                         }

                    /* }catch (NullPointerException ex){
                         Toast.makeText(context,ex.toString(),Toast.LENGTH_SHORT).show();
                     }

                    */
               }
                if((indexR!=1&&indexC==2)&&(info!=null)){

                    if(!info.equalsIgnoreCase(longitud)){
                        txtCell.setBackgroundColor(Color.RED);
                    }
                }

                if(indexC==1){
                    latitud=info;
                }
                if(indexC==2){
                    longitud=info;
                }


                tableRow.addView(txtCell,newTableRowParams());
            }
            tableRow.setBackgroundColor(Color.BLACK);
            tableLayout.addView(tableRow);
        }

    }

    public void addItems(String[]item){
    String info;
    data.add(item);
    indexC=0;
    newRow();
    while (indexC<header.length){
        newCell();
        info=(indexC<item.length)?item[indexC++]:"";
        txtCell.setText(info);
        txtCell.setBackgroundColor(Color.CYAN);
        txtCell.setTextColor(Color.BLACK);
        tableRow.addView(txtCell,newTableRowParams());
    }
    tableLayout.addView(tableRow,data.size()-1);
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params=new TableRow.LayoutParams();
        params.setMargins(1,1,2,1);
        params.weight=1;

        return params;
    }

    public void backgroundHeader(int color){
        indexC=0;
        while (indexC<header.length){

            txtCell=getCell(0,indexC++);
            txtCell.setBackgroundColor(color);

        }
        tableLayout.addView(tableRow);
    }

    private TableRow getRow(int index){
        return (TableRow)tableLayout.getChildAt(index);
    }

    private TextView getCell(int rowIndex,int columIndex){
        tableRow=getRow(rowIndex);
        return (TextView)tableLayout.getChildAt(columIndex);
    }

}

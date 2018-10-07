package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
public class net_banking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_banking);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(net_banking.this,
        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
       mySpinner.setOnItemSelectedListener(this);




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        final Intent intent;
        final Uri uri;
        switch (i) {
            case 0:
                Toast.makeText(net_banking.this, "Please select a bank",
                        Toast.LENGTH_LONG).show();
                break;
            case 1:
                uri = Uri.parse("https://retail.axisbank.co.in/wps/portal/rBanking/axisebanking/AxisRetailLogin/!ut/p/a1/04_Sj9CPykssy0xPLMnMz0vMAfGjzOKNAzxMjIwNjLwsQp0MDBw9PUOd3HwdDQwMjIEKIoEKDHAARwNC-sP1o_ArMYIqwGNFQW6EQaajoiIAVNL82A!!/dl5/d5/L2dBISEvZ0FBIS9nQSEh/?_ga=2.197597423.155801593.1531046850-450485730.1531046850");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                break;
            case 2:
                uri = Uri.parse("https://www.bobibanking.com/BankAwayRetail/(S(dbpwt0jtpjy3q255qifw3555))/web/L001/retail/jsp/arcot/pages/RetailLogin.aspx?RequestId=19051901");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case 3:
                uri = Uri.parse("https://www.mahaconnect.in/jsp/index.html");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case 4:
                uri = Uri.parse("https://starconnectcbs.bankofindia.com/BankAwayRetail/(S(0lf50oaqdmrgo4fkpjalcb55))/web/L001/retail/jsp/user/RetailSignOn.aspx?RequestId=8958711");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case 5:
                uri = Uri.parse("https://netbanking.canarabank.in/entry/ENULogin.jsp");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case 6:
                uri = Uri.parse("https://denaiconnect.denabank.co.in/corp/AuthenticationController?__START_TRAN_FLAG__=Y&FORMSGROUP_ID__=AuthenticationFG&__EVENT_ID__=LOAD&FG_BUTTONS__=LOAD&ACTION.LOAD=Y&AuthenticationFG.LOGIN_FLAG=1&BANK_ID=018&LANGUAGE_ID=001");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case 7:
                uri = Uri.parse("https://netbanking.hdfcbank.com/netbanking/?_ga=2.25576948.876642295.1531047396-1822441210.1531047396");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case 8:
                uri = Uri.parse("https://infinity.icicibank.com/corp/AuthenticationController?FORMSGROUP_ID__=AuthenticationFG&__START_TRAN_FLAG__=Y&FG_BUTTONS__=LOAD&ACTION.LOAD=Y&AuthenticationFG.LOGIN_FLAG=1&BANK_ID=ICI");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case 9:
                uri = Uri.parse("https://retail.onlinesbi.com/retail/login.htm");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case 10:
                uri = Uri.parse("https://personal.yesbank.co.in/netbanking/entry");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }

        @Override
        public void onNothingSelected (AdapterView < ? > adapterView){
            Toast.makeText(net_banking.this, "Please select a bank",
                    Toast.LENGTH_LONG).show();
        }
    }
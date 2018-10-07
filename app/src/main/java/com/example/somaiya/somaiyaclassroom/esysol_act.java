package com.example.somaiya.somaiyaclassroom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

import Config.Config;

public class esysol_act extends AppCompatActivity {
   public static final int PAYPAL_REQUEST_CODE=7171;
   private static PayPalConfiguration config=new PayPalConfiguration()
           .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
           .clientId(Config.PAYPAL_CLIENT_ID);

    Button btnPayNow;
    String amount="5";

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esysol_act);
        Intent intent=new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);

        Magnify mag = new Magnify();
        float zoomFactor = 1.25f;
        if (Magnify.getInstance().getData())
            mag.enlarge(true, findViewById(android.R.id.content), zoomFactor);
        btnPayNow=(Button)findViewById(R.id.btnPayNow);
        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });

        }
private void processPayment(){
    PayPalPayment payPalPayment=new PayPalPayment(new BigDecimal(String.valueOf(amount)),"USD","Payment for Easysolution",
            PayPalPayment.PAYMENT_INTENT_SALE);
    Intent intent=new Intent(this, PaymentActivity.class);
    intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
    intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
    startActivityForResult(intent,PAYPAL_REQUEST_CODE);

}

    public void Pay(View V){

            Intent intent = new Intent(esysol_act.this, net_banking.class);
            startActivity(intent);
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(requestCode==PAYPAL_REQUEST_CODE){
        if(resultCode==RESULT_OK){
            PaymentConfirmation confirmation=data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if(confirmation!=null){
                try{
                    String paymentDetails=confirmation.toJSONObject().toString(4);
                    startActivity(new Intent(this,PaymentDetails.class)
                            .putExtra("PaymentDetails",paymentDetails)
                            .putExtra("PaymentAmount",amount)
                    );
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode== Activity.RESULT_CANCELED)
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show();
    }
    else if(resultCode==PaymentActivity.RESULT_EXTRAS_INVALID)
        Toast.makeText(this,"Invalid",Toast.LENGTH_SHORT).show();

    }
}


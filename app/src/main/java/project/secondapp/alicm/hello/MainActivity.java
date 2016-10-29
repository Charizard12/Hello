package project.secondapp.alicm.hello;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int cantidad = 0;
    float precio = 2.50f;
    String summary = "";
    boolean order = false;
    String name = "";
    EditText nameEd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEd = (EditText) findViewById(R.id.nameET);
    }

    public void incrementar(View view){
        cantidad++;
        displayQuantity();
        //display(cantidad);
    }
    public void decrementar(View view){
        if(cantidad <= 0){
            cantidad = 0;
        } else{
            cantidad--;
        }
        displayQuantity();
        //display(cantidad);
    }

    public void submitOrder(View view){ displaySummary(); }

    public void sendOrder(View view) { sendSummary(); }

    private void displaySummary(){
        TextView orderSum = (TextView) findViewById(R.id.precioTv);
        CheckBox cream = (CheckBox) findViewById(R.id.creamCB);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolateCB);

        String noOrder = getResources().getString(R.string.noOrder);

        name = nameEd.getText().toString();
        String creamString = "\n" + getResources().getString(R.string.cream) + ": ";
        String chocoString = "\n" + getResources().getString(R.string.choco) + ": ";
        String quantString = "\n" + getResources().getString(R.string.quantity) + ": ";
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);

        if ((cantidad > 0) && (name != "")) {

            float total = (precio + (cream.isChecked() ? 1.0f : 0.0f) + (chocolate.isChecked() ? 1.5f : 0.0f)) * cantidad;

            summary = getString(R.string.summaryName,name)
                    + creamString + (cream.isChecked() ? yes : no)
                    + chocoString + (chocolate.isChecked() ? yes : no)
                    + quantString + cantidad
                    + "\nTotal: $" + total
                    + "\n" + getString(R.string.thankYou);

            orderSum.setText(summary);
            order = true;
        }
        else{
            Toast.makeText(MainActivity.this, noOrder, Toast.LENGTH_SHORT).show();
            order = false;
        }
    }

    private void sendSummary(){
        //Action_Sento para crear un correo electronico sin direccion
        if(order){
            String subject = getString(R.string.orderFrom) + name;
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, summary);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.orderFirst), Toast.LENGTH_SHORT).show();
        }
        
    }

    private void displayQuantity(){
        TextView cantidadTv = (TextView) findViewById(R.id.cantidadTv);
        cantidadTv.setText("" + cantidad);
    }

    public void nextActivity(View view){
        Intent intent =  new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }


}

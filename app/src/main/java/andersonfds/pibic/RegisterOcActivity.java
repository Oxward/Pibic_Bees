package andersonfds.pibic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterOcActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_oc);
    }

    public void buttonPress(View view)
    {
        switch ( view.getId() )
        {
            case R.id.imgTiraFoto:
                break;

            case R.id.btGal:
                break;

            case R.id.btEnv:
                break;

            case R.id.btCanc:
                break;

        }
    }

    public void buttonTeste( View v)
    {
        if(v.getId() == R.id.btTestar)
        {
            EditText txtNome = findViewById(R.id.txtNome);
            EditText txtCont = findViewById(R.id.txtCont);

            String nome, contato;
            nome = txtNome.getText().toString();
            contato = txtCont.getText().toString();

            TextView teste = findViewById(R.id.labelTesteNome);
            TextView teste2 = findViewById(R.id.labelTesteContato);
            teste.setText(""+nome);
            teste2.setText(""+contato);

        }
    }

}

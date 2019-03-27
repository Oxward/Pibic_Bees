package andersonfds.pibic.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import andersonfds.pibic.R;
import andersonfds.pibic.classes.Products;
import andersonfds.pibic.database.Repository;
import es.dmoral.toasty.Toasty;

public class AddProductsActivity extends AppCompatActivity {

    private static final String TAG = "AddProductsActivity";
    private static final int ACTION_PICK_PHOTO = 1;
    private static final int ACTION_TAKE_PHOTO = 2;

    private EditText txtDesc, txtVal;
    private ImageView imgProd;
    private Button btFoto, btSave;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        repository = new Repository(this);

        initializeFields();
        viewListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case ACTION_PICK_PHOTO:
                if (data != null) {
                    Uri uri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        imgProd.setImageBitmap(bitmap);
                        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        Toasty.error(this, "Erro ao carregar imagem!").show();
                    }
                }
                break;

            case ACTION_TAKE_PHOTO:
                Bitmap photo;
                if (data != null) {
                    photo = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                    imgProd.setImageBitmap(photo);
                    Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
                } else {
                    Toasty.error(this, "Erro ao capturar foto!").show();
                }
                break;

        }
    }

    private void initializeFields() {
        txtDesc = findViewById(R.id.txtDescProdSave);
        txtVal = findViewById(R.id.txtValProdSave);
        imgProd = findViewById(R.id.imgProdSave);
        btFoto = findViewById(R.id.btFotoProd);
        btSave = findViewById(R.id.prodSave);
    }

    private void viewListeners() {
        btFoto.setOnClickListener(view -> showPictureDialog());

        btSave.setOnClickListener(view -> {
            if (TextUtils.isEmpty(txtDesc.getText().toString())) {
                txtDesc.setError("Preencha");
                return;
            }

            if (TextUtils.isEmpty(txtVal.getText().toString())) {
                txtDesc.setError("Preencha");
                return;
            }

            String desc = txtDesc.getText().toString();
            double val = Double.parseDouble(txtVal.getText().toString());
            byte[] foto = convertBitmapToByteArray(((BitmapDrawable) imgProd.getDrawable()).getBitmap());

            Products products = new Products(desc, val, foto);
            long[] salvo = saveProductToDatabase(products);
            if (salvo[0] != -1) {
                Toasty.success(getApplicationContext(), "Produto salvo").show();
            } else {
                Toasty.error(getApplicationContext(), "Produto ñ salvo").show();
            }

        });

    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void showPictureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escolha uma ação para continuar:");
        String[] itens = {"Escolher uma imagem da galeria", "Tirar foto com a câmera"};
        builder.setItems(itens, (dialog, which) -> {
            switch (which) {
                case 0:
                    openGallery();
                    break;

                case 1:
                    takePhoto();
                    break;
            }
        });
        builder.show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, ACTION_PICK_PHOTO);
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ACTION_TAKE_PHOTO);
    }

    private long[] saveProductToDatabase(Products products) {
        return repository.insertProduct(products);
    }

}

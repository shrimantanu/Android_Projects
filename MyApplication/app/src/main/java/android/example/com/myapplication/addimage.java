package android.example.com.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.widget.Toast.LENGTH_LONG;

public class addimage extends AppCompatActivity {
private ImageView imageView;
private FirebaseAuth.AuthStateListener mauth;
private FirebaseStorage mfbstorage;
private EditText t1;
private Button b1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("imag");
private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addimage);
        b1=(Button)findViewById(R.id.b0);
        imageView =(ImageView)findViewById(R.id.i0);
      //  mauth= (FirebaseAuth.AuthStateListener) FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              exsto();
            }
        });
    }
    protected void cam(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 0);
    }
    protected void exsto(){

        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(pickPhoto, 1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Uri selectedImage = null;
        selectedImage = imageReturnedIntent.getData();
        imageView.setImageURI(selectedImage);

       /* switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    imageView.setImageURI(selectedImage);

                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                     selectedImage = imageReturnedIntent.getData();
                    imageView.setImageURI(selectedImage);
                }
                break;
        }*/
        StorageReference riversRef = mStorageRef.child("truuio"+t1.getText()+".jpeg");
        riversRef.putFile(selectedImage)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                   b1.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Uri downloadUrl = taskSnapshot.getDownloadUrl();
                           t1=(EditText) findViewById(R.id.e1);
                           FriendlyMessage fh=new FriendlyMessage(t1.getText().toString(),"dsa",downloadUrl.toString(),"0");
                           myRef.push().setValue(fh);
                       }
                   });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }
    private void selectImage() { final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(addimage.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utility.checkPermission(addimage.this);
                if (items[item].equals("Take Photo")) {
                   // userChoosenTask="Take Photo";
                    if(result)
                        cam();
                } else if (items[item].equals("Choose from Library")) {
                    //userChoosenTask="Choose from Library";
                    if(result)
                        exsto();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();    }


}

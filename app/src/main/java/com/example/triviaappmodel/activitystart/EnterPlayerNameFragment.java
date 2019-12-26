package com.example.triviaappmodel.activitystart;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.triviaappmodel.activityscore.ScoreActivity;
import com.example.triviaappmodel.activityselection.DialogFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import mk.codeacademy.triviaquizz.R;

public class EnterPlayerNameFragment extends DialogFragment {

    private FirebaseAuth auth;
    private String email;
    private String password;
    private String nick;
    private EditText emailEt;
    private EditText passEt;
    private EditText nickName;

    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.enter_name_fragment, container, false);

        auth=FirebaseAuth.getInstance();

        emailEt=view.findViewById(R.id.player_email_et);
        passEt=view.findViewById(R.id.player_password_et);
        nickName=view.findViewById(R.id.player_nickname_);

        ImageButton button = view.findViewById(R.id.enterBtn_);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=emailEt.getText().toString();
                password=passEt.getText().toString();
                nick=nickName.getText().toString();


                Intent intent = new Intent(getActivity(), ScoreActivity.class);
                startActivity(intent);


//                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//
//                    Toast.makeText(getContext(), "Please fill all the fields",
//                            Toast.LENGTH_SHORT).show();
//
//
//                } else {
//
//                    auth.createUserWithEmailAndPassword(email, password)
//                            .addOnCompleteListener(getActivity(),
//                                    new OnCompleteListener<AuthResult>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                            if (task.isSuccessful()) {
//
//                                                Toast.makeText(mContext,
//                                                        "Successful Registered",
//                                                        Toast.LENGTH_SHORT).show();
//                                            } else {
//
//                                                Toast.makeText(mContext,
//                                                        "Registration Failed", Toast.LENGTH_LONG).show();
//                                            }
//                                        }
//                                    });
//
//                    passEt.setText("");
//
//                    Intent intent=new Intent(getActivity(),ScoreActivity.class);
//                    startActivity(intent);
//
//                    dismiss();
//
//                }

            }
        });

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}

package e.rezeda.chat;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import e.rezeda.chat.Models.User;
import e.rezeda.chat.databinding.ActivityUserLoginBinding;


public class UserLoginActivity extends AppCompatActivity {

    private UserLoginViewModel viewModel;
    private ActivityUserLoginBinding binding;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBinding(savedInstanceState);
    }

    private void setBinding(Bundle savedInstanceState){
        viewModel = ViewModelProviders.of(this).get(UserLoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_login);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        setupButtonClick();
    }




    private void setupButtonClick() {
        viewModel.getButtonClick().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Toast.makeText(UserLoginActivity.this,
                        "Email " + viewModel.getUser() + ", Password " ,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



//    public void onButtonLoginClicked(){
//        String username = binding.editUsername.toString();
//        String password = binding.editPassword.toString();
//        SharedPreferences shared = getSharedPreferences("shared", MODE_PRIVATE);
//        if(shared.contains("username") && shared.contains("password")){
//            Intent myIntent = new Intent(this, MainActivity.class);
//            startActivity(myIntent);
//        } else {
//            saveInformation(binding.editUsername.toString(),binding.editPassword.toString());
//            Intent myIntent = new Intent(this, MainActivity.class);
//            startActivity(myIntent);
//        }
//    }
//
//
//    public void saveInformation(String username,String password) {
//        SharedPreferences shared = getSharedPreferences("shared", MODE_PRIVATE);
//        SharedPreferences.Editor editor = shared.edit();
//        editor.putString("username", username);
//        editor.putString("password", password);
//        editor.commit();
//    }



}
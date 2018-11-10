package e.rezeda.chat;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import e.rezeda.chat.Services.EchoWebSocketListener;
import e.rezeda.chat.databinding.ActivityUserLoginBinding;

public class MainActivity extends AppCompatActivity {

    EchoWebSocketListener listener = new EchoWebSocketListener();
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
   //         viewModel.init();
        }
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

//    private void start() {
//        Request request = new Request.Builder().url("ws://85.255.1.214:8083").build();
//        EchoWebSocketListener listener = new EchoWebSocketListener();
//        WebSocket ws = client.newWebSocket(request, listener);
//
//        client.dispatcher().executorService().shutdown();
//    }
//
//    private void output(final String txt) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                output.setText(output.getText().toString() + "\n\n" + txt);
//            }
//        });
//    }


}

package e.rezeda.chat;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getContext();
       // assertEquals("e.rezeda.chat", appContext.getPackageName());
    }

    @Test
    public  void testChatRooms(){
        ChatRoomsViewModel chatRoomsViewModel = new ChatRoomsViewModel();
        chatRoomsViewModel.init();
        chatRoomsViewModel.askForUpdateChatRooms();
        try{
            Thread.sleep(2000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        MutableLiveData<List<ChatRoom>> chatRooms = chatRoomsViewModel.getChatRooms();

        assertEquals("chatRooms not loaded", 2, chatRooms.getValue().size());



    }

}

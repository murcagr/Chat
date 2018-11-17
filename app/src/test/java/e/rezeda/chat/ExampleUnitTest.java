package e.rezeda.chat;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import androidx.test.core.app.ApplicationProvider;
import org.junit.Rule;
import org.junit.Test;

import e.rezeda.chat.Models.Message;
import e.rezeda.chat.Models.User;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    User user1 = new User("user1");
    User user2 = new User("user2");
    User user3 = new User("user3");
    Message msg1 = new Message("user0","user1","msg1");
    Message msg2 = new Message("user0","user2","msg2");
    Message msg3 = new Message("user1","user0","msg3");
    Message msg4 = new Message("user2","user0","msg4");
    Message msg5 = new Message("user3","user0","msg5");

    @Test
    public void test_Users() {
        User user1 = new User("user1");
        User user2 = new User("user2", "qwerty123");

        assertEquals("user1 username is not user1","user1", user1.getUsername());
        assertEquals("user2 username is not user2","user2", user2.getUsername());


        assertEquals("user1 password is not null",null, user1.getPassword());
        assertEquals("user2 password is not qwerty123","qwerty123", user2.getPassword());
    }

    @Test
    public void test_UserValidationOffline(){
        User user1 = new User("Uuser1", "qwerty123");
        User user2 = new User("us", "qwe");
        assertEquals("user1 username is not valid",true, user1.isUsernameValid());
        assertEquals("user1 password is not valid",true, user1.isPasswordValid());
        assertEquals("user1 username or password is valid",true, user1.isUsernamePasswordValid());

        assertEquals("user2 username is valid",false, user2.isUsernameValid());
        assertEquals("user2 password is valid",false, user2.isPasswordValid());
        assertEquals("user2 username or password is valid",false, user2.isUsernamePasswordValid());
    }

    @Test
    public void test_ChatMessages() {
        User user1 = new User("user1");
        ChatMessages chatMessages = new ChatMessages("user1");
        assertEquals("Chat room has been created with messages",0, chatMessages.getChatMessageList().size());
        assertEquals("ChatMessages has been creates with wrong user",chatMessages.getTo_who(), user1.getUsername());
        chatMessages.addChatMessage(new ChatMessage("lalala","user1"));
        assertEquals("Chat room appended more or less than 1 message",chatMessages.getChatMessages().getValue().size(), 1);
    }

    @Test
    public void test_ChatRoomsAdding(){
        ChatRooms chatRooms = new ChatRooms();
        assertEquals("ChatRooms has been " +
                "creates with wrong count of elements",0, chatRooms.getChatRoomList().size());

        chatRooms.addChatRoom(new ChatRoom("chatRoom1","text"));
        assertEquals("Didn't added new ChatRoom ",1, chatRooms.getChatRoomList().size());
        chatRooms.addChatRoom(new ChatRoom("chatRoom2","text"));
        chatRooms.addChatRoom(new ChatRoom("chatRoom3","text"));
        chatRooms.addChatRoom(new ChatRoom("chatRoom4","text"));
        chatRooms.addChatRoom(new ChatRoom("chatRoom5","text"));
        chatRooms.addChatRoom(new ChatRoom("chatRoom6","text"));
        assertEquals("Didnt added 6 ChatRooms", 6, chatRooms.getChatRoomList().size());
    }
    @Test
    public void test_ChatRoomsDeleting(){
        ChatRooms chatRooms = new ChatRooms();
        chatRooms.addChatRoom(new ChatRoom("chatRoom1","text"));
        chatRooms.addChatRoom(new ChatRoom("chatRoom2","text2"));

        chatRooms.delChatRoom(1);
        assertEquals("Didn't delete last ChatRoom",1, chatRooms.getChatRoomList().size());

        chatRooms.addChatRoom(new ChatRoom("chatRoom2","text"));
        chatRooms.addChatRoom(new ChatRoom("chatRoom3","text"));
        chatRooms.addChatRoom(new ChatRoom("chatRoom4","text"));
        chatRooms.addChatRoom(new ChatRoom("chatRoom5","text"));
        chatRooms.addChatRoom(new ChatRoom("chatRoom6","text"));
        chatRooms.delAllChatRooms();
        assertEquals("Didnt delete all ChatRooms", 0, chatRooms.getChatRoomList().size());
    }

}
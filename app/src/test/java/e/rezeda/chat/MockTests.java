package e.rezeda.chat;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

import com.example.MessageEvent;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MockTests {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    //Function for equal to Lists
    <T, U> boolean equalLists(List<T> list1, List<T> list2, Function<T, U> mapper) {
        List<U> first = list1.stream().map(mapper).collect(Collectors.toList());
        List<U> second = list2.stream().map(mapper).collect(Collectors.toList());
        return first.equals(second);
    }

    @Test
    public void test_fetchChatMessages() {
        //подготавливаем
        MessageEvent messageEvent = mock(MessageEvent.class);
        JSONChatListMessages JSONChatListMessage1 = new JSONChatListMessages(5,"text1",0,"admin");
        JSONChatListMessages JSONChatListMessage2 = new JSONChatListMessages(5,"text2",0,"admin");
        JSONChatListMessages JSONChatListMessage3 = new JSONChatListMessages(5,"text3",0,"admin");
        List<JSONChatListMessages> listMessagesList = new ArrayList<JSONChatListMessages>();
        listMessagesList.add(JSONChatListMessage1);
        listMessagesList.add(JSONChatListMessage2);
        listMessagesList.add(JSONChatListMessage3);
        ChatMessages chatMessages = new ChatMessages();
        //определяем
        when(messageEvent.getData()).thenReturn(listMessagesList);
        when(messageEvent.getType()).thenReturn("getMessagesForChatResult");
        //сравниваем
        chatMessages.fetchList(messageEvent);
        MatcherAssert.assertThat(chatMessages.getChatMessageList(),
                hasItems(new ChatMessage("admin","text1"),
                        new ChatMessage("admin","text3"),
                        new ChatMessage("admin","text2")
                ));
    }

    @Test
    public void test_GetChatRoom() {
        //подготавливаем
        ChatRoom chatRoomMock = mock(ChatRoom.class);

        ChatRoom chatRoom = new ChatRoom("ChatRoom1","text");
        //определяем
        when(chatRoomMock.getName()).thenReturn("ChatRoom1");
        //сравниваем
        Assert.assertEquals("ChatRoom1",chatRoomMock.getName());


    }
}
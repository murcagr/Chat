package e.rezeda.chat.Adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import e.rezeda.chat.BR;
import e.rezeda.chat.ChatRoomsViewModel;
import e.rezeda.chat.ChatRoom;

public class ChatRoomsAdapter extends RecyclerView.Adapter<ChatRoomsAdapter.GenericViewHolder> {

    private int layoutId;
    private List<ChatRoom> chatRooms;
    private ChatRoomsViewModel viewModel;


    public ChatRoomsAdapter(@LayoutRes int layoutId, ChatRoomsViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }


    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return chatRooms == null ? 0 : chatRooms.size();
    }

    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setChatRooms(List<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ChatRoomsViewModel viewModel, Integer position) {

            binding.setVariable(BR.ChatRoomsViewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}

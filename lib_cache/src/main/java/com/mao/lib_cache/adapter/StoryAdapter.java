package com.mao.lib_cache.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.lib_cache.R;
import com.mao.lib_cache.databinding.ItemStoryBinding;
import com.mao.lib_cache.model.Story;

import java.util.List;

/**
 * @author zhangkun
 * @time 2020/10/15 11:56 AM
 * @Description
 */
public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private List<Story> stories;

    public StoryAdapter(List<Story> stories) {
        this.stories = stories;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
        ItemStoryBinding itemStoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_story, parent, false);
        return new StoryViewHolder(itemStoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        holder.itemStoryBinding.setItem(stories.get(position));
        holder.itemStoryBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    static class StoryViewHolder extends RecyclerView.ViewHolder {

        ItemStoryBinding itemStoryBinding;

        public StoryViewHolder(ItemStoryBinding binding) {
            super(binding.getRoot());
            itemStoryBinding = binding;
        }
    }
}

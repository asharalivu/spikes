package com.novoda.bonfire.channel.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.novoda.bonfire.channel.data.model.Channel;
import com.novoda.bonfire.channel.data.model.Channels;
import com.novoda.bonfire.channel.displayer.ChannelsDisplayer;

import java.util.ArrayList;

class ChannelsAdapter extends RecyclerView.Adapter<ChannelViewHolder>  {

    private Channels channels = new Channels(new ArrayList<Channel>());
    private ChannelsDisplayer.ChannelSelectionListener channelSelectionListener;

    public void update(Channels channels){
        this.channels = channels;
        notifyDataSetChanged();
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChannelViewHolder(new ChannelView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        holder.bind(channels.getChannelAt(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    @Override
    public long getItemId(int position) {
        return channels.getChannelAt(position).hashCode();
    }

    public void attach(ChannelsDisplayer.ChannelSelectionListener channelSelectionListener) {
        this.channelSelectionListener = channelSelectionListener;
    }

    public void detach(ChannelsDisplayer.ChannelSelectionListener channelSelectionListener) {
        this.channelSelectionListener = ChannelsDisplayer.ChannelSelectionListener.NO_OP;
    }

    private final ChannelViewHolder.ChannelSelectionListener clickListener = new ChannelViewHolder.ChannelSelectionListener() {
        @Override
        public void onChannelSelected(Channel channel) {
            ChannelsAdapter.this.channelSelectionListener.onChannelSelected(channel);
        }
    };
}
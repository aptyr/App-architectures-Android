package aptyr.com.architecture.android.mvp.view.users;

/**
 * Copyright (C) 2016 Aptyr (github.com/aptyr)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import aptyr.com.architecture.android.mvp.R;
import aptyr.com.architecture.android.mvp.model.User;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    private List<User> mUsers = new ArrayList<>();
    private ViewHolder mViewHolder;
    private ItemClickListener mItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        mViewHolder = new ViewHolder(view);
        mViewHolder.mItemClickListener = mItemClickListener;
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.rowView.setData(user);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void addUsers(List<User> users) {
        mUsers.addAll(users);
        notifyItemRangeInserted(mUsers.size() - users.size(), users.size());
    }

    public void setItemClickListener(ItemClickListener clickListener) {
        mItemClickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private UsersRecyclerViewRow rowView;
        private ItemClickListener mItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            rowView = (UsersRecyclerViewRow) itemView.findViewById(R.id.row);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }

    }
}

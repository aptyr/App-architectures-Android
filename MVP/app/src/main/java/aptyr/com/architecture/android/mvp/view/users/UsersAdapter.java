package aptyr.com.architecture.android.mvp.view.users;

/**
 * Copyright (C) 2016 Aptyr (github.com/aptyr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import aptyr.com.architecture.android.mvp.R;
import aptyr.com.architecture.android.mvp.BR;
import aptyr.com.architecture.android.mvp.model.User;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> mUsers = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false);

        ViewHolder vh = new ViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.getDataBinding().setVariable(BR.user, user);
        holder.getDataBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void addUsers(List<User> users) {
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    @Nullable
    public User getLast() {
        if(mUsers.size() > 0) {
           return mUsers.get(mUsers.size() - 1);
        }
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding dataBinding;

        public ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            dataBinding = itemView;
        }

        public ViewDataBinding getDataBinding() {
            return dataBinding;
        }
    }
}

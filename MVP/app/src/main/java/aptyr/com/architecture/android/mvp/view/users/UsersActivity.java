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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import aptyr.com.architecture.android.mvp.R;
import aptyr.com.architecture.android.mvp.model.User;
import aptyr.com.architecture.android.mvp.view.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersActivity extends AppCompatActivity implements
        UsersContract.View,
        UsersAdapter.ItemClickListener,
        RecyclerView.RecyclerViewBottomReachedListener {


    private UsersContract.Presenter mPresenter;

    @BindView(R.id.recycler_view)
    protected RecyclerView rv;

    private UsersAdapter adapter = new UsersAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);


        rv.setAdapter(adapter);
        rv.setRecyclerViewBottomReachedListener(this);
        adapter.setItemClickListener(this);

        getPresenter().start();
    }


    @Override
    public UsersContract.Presenter getPresenter() {
        if (mPresenter == null) {
            mPresenter = new UsersPresenter();
            mPresenter.setView(this);
        }
        return mPresenter;
    }

    @Override
    public void onUsersFetched(List<User> users) {
        adapter.addUsers(users);
    }

    @Override
    public void bottomReached() {
        getPresenter().getUsers(adapter.getItemCount());
    }

    @Override
    public void onItemClick(int position) {
        getPresenter().rvItemClicked(position);
    }
}

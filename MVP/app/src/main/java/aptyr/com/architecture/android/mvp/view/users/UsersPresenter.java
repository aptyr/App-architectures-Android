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

import java.util.ArrayList;
import java.util.List;

import aptyr.com.architecture.android.mvp.model.User;
import aptyr.com.architecture.android.mvp.rest.GithubAPI;

public class UsersPresenter implements UsersContract.Presenter, GithubAPI.FetchUsersListener {

    private UsersContract.View mView;

    private GithubAPI api = new GithubAPI();

    private List<User> data = new ArrayList<>();

    @Override
    public void start() {
        if (mView == null) {
            throw new UnsupportedOperationException("Call setView");
        }

        api.setFetchUsersListener(this);

        getUsers(0);
    }

    @Override
    public void setView(UsersContract.View view) {
        mView = view;
    }

    @Override
    public void usersFetched(List<User> users) {
        if (users != null) {
            data.addAll(users);
            mView.onUsersFetched(users);
        }
    }

    @Override
    public void getUsers(int since) {
        api.getUsers(since);
    }

    @Override
    public void rvItemClicked(int position) {
        mView.expandRow(position);
    }
}

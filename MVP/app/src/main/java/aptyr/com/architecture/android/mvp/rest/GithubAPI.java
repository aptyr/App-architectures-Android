package aptyr.com.architecture.android.mvp.rest;
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

import java.util.List;

import aptyr.com.architecture.android.mvp.model.User;
import aptyr.com.architecture.android.mvp.rest.base.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GithubAPI implements Callback<List<User>> {

    public interface FetchUsersListener {
        void usersFetched(List<User> users);
    }

    private GithubService restService;

    private FetchUsersListener fetchUsersListener;

    public GithubAPI() {
        restService = RetrofitClient.createRetrofitService(GithubService.class, GithubService.SERVICE_ENDPOINT);
    }

    public void setFetchUsersListener(FetchUsersListener fetchUsersListener) {
        this.fetchUsersListener = fetchUsersListener;
    }

    public void getUsers(int since) {
        restService.getUsers(since).enqueue(this);
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if(fetchUsersListener != null){
            fetchUsersListener.usersFetched(response.body());
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {

    }
}

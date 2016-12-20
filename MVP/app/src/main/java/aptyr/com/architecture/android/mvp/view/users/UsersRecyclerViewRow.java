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
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import aptyr.com.architecture.android.mvp.R;
import aptyr.com.architecture.android.mvp.databinding.LayoutBasicUserBinding;
import aptyr.com.architecture.android.mvp.model.User;

public class UsersRecyclerViewRow extends ConstraintLayout {

    private User mUser;
    private LayoutBasicUserBinding mDataBinding;

    public UsersRecyclerViewRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.layout_basic_user, this, true);
    }


    public void setData(User user){
        mUser = user;
        mDataBinding.setUser(user);
        mDataBinding.setExpanded(false);
    }
}

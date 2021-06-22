package com.gildstudios.mister.abstracts;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.gildstudios.mister.R;


abstract public class AwareFragment extends Fragment {

    protected boolean mViewExisted;

    protected Toast mToast;
    protected View mItView;

    public View onPersistentView(@NonNull LayoutInflater inflater, ViewGroup viewGroup,
                                 int layoutId, boolean attachToRoot) {
        mViewExisted = mItView != null;

        if (!mViewExisted) {
            mItView = inflater.inflate(layoutId,
                    viewGroup, attachToRoot);
        }

        return mItView;
    }

    public View onPersistentView(@NonNull LayoutInflater inflater,
                                 ViewGroup viewGroup, int layoutId) {
        return onPersistentView(inflater, viewGroup,
                                layoutId,false);
    }

    @Override
    public void onDestroy() {
        if (mItView.getParent() != null) {
            ((ViewGroup) mItView.getParent())
                    .removeView(mItView);
        }
        mItView = null;

        super.onDestroy();
    }

    protected <T> boolean activityExists(Class<T> activityType) {
        try {
            T parentActivity = activityType.cast(getActivity());
            return parentActivity != null;
        } catch(ClassCastException e) {
            return false;
        }
    }

    protected <T> boolean activityExists() {
        return activityExists(FragmentActivity.class);
    }

    protected void showToast(String message, int duration) {
        if (!activityExists())
            return;

        if (mToast != null)
            mToast.cancel();

        mToast = Toast.makeText(getContext(),
                                message,
                                duration);

        mToast.show();
    }

    protected void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    protected void showUnknownError() {
        showToast("Qualcosa è andato storto. " +
                  "Riprova tra qualche minuto.");
    }

}
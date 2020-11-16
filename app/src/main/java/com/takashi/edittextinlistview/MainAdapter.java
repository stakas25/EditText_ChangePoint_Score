package com.takashi.edittextinlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class MainAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private boolean mIsInputScore;
    private Context mContext;

    public MainAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mInflater.inflate(R.layout.item_main, viewGroup, false);
        }

        final EditText editText = view.findViewById(R.id.edit);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mIsInputScore){
                    //スコア入力モード
                    showToast("スコア入力モード");

                    //EditTextのタップとして扱うためfalseを返す.
                    return false;
                }else {
                    //点数入力モード
                    showToast("点数入力モード");

                    //EditTextのタップとして扱わないためtrueでイベントを消化する.
                    return true;
                }
            }
        });

        return view;
    }

    public void setInputScore(Boolean isInputPoint){
        this.mIsInputScore = isInputPoint;
    }

    private void showToast(String toastMessage){
        Toast toast = Toast.makeText(mContext, toastMessage, Toast.LENGTH_LONG);
        toast.show();
    }
}

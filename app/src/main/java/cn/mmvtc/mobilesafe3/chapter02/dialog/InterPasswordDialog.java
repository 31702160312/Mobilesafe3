package cn.mmvtc.mobilesafe3.chapter02.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.mmvtc.mobilesafe3.R;

/**
 * 自定义对话框：输入密码对话框的逻辑代码
 */

public class InterPasswordDialog extends Dialog implements View.OnClickListener {

    private TextView mTitleTV;//对话框标题
    private EditText mInterET;//输入密码文本框
    private Button mOKBtn;//确认按钮
    private Button mCancleBtn;//取消按钮
    private MyCallBack myCallBack;
    private Context context;

    public InterPasswordDialog(Context context) {
        super(context,R.style.dialog_custom);
        this.context = context;
    }
    public void setCallBack(MyCallBack myCallBack){
        this.myCallBack = myCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inter_password_dialog);
        initView();
    }

    private void initView() {
        mTitleTV = (TextView) findViewById(R.id.tv_interpwd_title);
        mInterET = (EditText) findViewById(R.id.et_inter_password);
        mOKBtn = (Button) findViewById(R.id.btn_comfirm);
        mCancleBtn = (Button) findViewById(R.id.btn_dismiss);
        mOKBtn.setOnClickListener(this);
        mCancleBtn.setOnClickListener(this);
    }
    public void setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            mTitleTV.setText(title);
        }
    }

    public String getPassword(){
        return mInterET.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_comfirm:
                myCallBack.confirm();
                break;
            case R.id.btn_dismiss:
                myCallBack.cancle();
                break;
        }
    }
    public interface MyCallBack{
        void confirm();
        void cancle();
    }
}

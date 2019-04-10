package cn.mmvtc.mobilesafe3.chapter02;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import cn.mmvtc.mobilesafe3.R;

/**
 * 向导3
 * 功能：用于选择或输入安全联系人，SIM卡发生变更，发短信给安全号码
 * */

@SuppressLint("ShowToast")
public class SetUp3Activity extends BaseSetUpActivity implements View.OnClickListener {
    private EditText mInputPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);
        initView();
    }
/**
 * 初始化控件
 * */
    private void initView() {
        ((RadioButton) findViewById(R.id.rb_third)).setChecked(true);
        findViewById(R.id.btn_addcontact).setOnClickListener(this);
        mInputPhone = (EditText) findViewById(R.id.et_inputphone);
        String safephone = sp.getString("safephone",null);
        if (!TextUtils.isEmpty(safephone)){
            mInputPhone.setText(safephone);
        }
    }

    @Override
    public void showNext() {
        //判断文本输入框中是否有电话号码
        String safePhone = mInputPhone.getText().toString().trim();
        if(TextUtils.isEmpty(safePhone)){
            Toast.makeText(this, "请输入安全号码", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("safephone",safePhone);
        edit.commit();
        startActivityAndFinishSelf(SetUp4Activity.class);
    }

    @Override
    public void showPre() {
        startActivityAndFinishSelf(SetUp2Activity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_addcontact:
                //跳转到联系人界面
                //startActivityForResult(new Intent(this,ContactSelectActivity.class),0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            String phone = data.getStringExtra("phone");
            mInputPhone.setText(phone);
        }
    }

}

package cn.mmvtc.mobilesafe3.chapter02.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import cn.mmvtc.mobilesafe3.chapter02.entity.ContactInfo;

import static android.R.attr.y;

/**
 * Created by Administrator on 2019/4/10.
 * 功能：联系人信息存储在SQLite数据库
 * 获取联系人的id，根据id在data表中查询联系人的姓名和电话号码
 * 并封装到ContactInfo类，然后存入到List集合
 */

public class ContactInfoParser {
    public static List<ContactInfo> getSystemContext(Context context){
        //①内容访问者获取
        ContentResolver resolver = context.getContentResolver();//内容访问者
        //②获取内容访问者的uri数据，查询raw_contacts表，把联系人的id取出来。（raw_contacts，data表）
       Uri uri = Uri.parse("content://com.android.contact/raw_contacts");
       Uri datauri = Uri.parse("content://com.android.contact/data");
        //③获取联系人表中的id，根据id获取联系人的姓名和电话号码
        List<ContactInfo>infos = new ArrayList<ContactInfo>();//存放信息
        //④查询raw_contacts的contact_id
        Cursor cursor = resolver.query(uri,new String[]{"contact_id"},null,null,null);
        //用while语句遍历游标cursor
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            if (id != null){
                System.out.print("联系人id："+id);
                ContactInfo info = new ContactInfo();
                info.id = id;
                //根据id查询data表的姓名和电话号码
                //系统API查询data表的时候，不是真正的查询data表，而是查询data表的视图。
                Cursor dataCursor = resolver.query(datauri,new String[]{
                        "data1","mimetype"},"raw_contact_id=?",new String[]{id},null);
                while (dataCursor.moveToNext()){
                    String data1 = dataCursor.getString(0);
                    String mimetype = dataCursor.getString(1);
                    if("vnd.android.cursor.item/name".equals(mimetype)){
                        System.out.print("姓名："+data1);
                        info.name=data1;
                    }else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)){
                        System.out.print("电话："+data1);
                        info.phone=data1;
                    }
                }
              infos.add(info);
                dataCursor.close();
            }
        }
       cursor.close();
        return infos;
    }

    public static List<ContactInfo> getSimContacts (Context context){
        Uri uri = Uri.parse("content://icc/adn");
        List<ContactInfo> infos = new ArrayList<ContactInfo>();
        Cursor mCursor = context.getContentResolver().query(uri,null,null,null,null);
        if (mCursor != null){
            while (mCursor.moveToNext()){
                ContactInfo info = new ContactInfo();
                //获取联系人姓名
                int nameFieldColumnIndex = mCursor.getColumnIndex("name");
                info.name = mCursor.getString(nameFieldColumnIndex);
                //获取联系人号码
                int numberFieldColumnIndex = mCursor.getColumnIndex("number");
                info.phone = mCursor.getString(numberFieldColumnIndex);
                infos.add(info);
            }
        }
        mCursor.close();
        return infos;
    }

}

package com.studyjams.js0261.checked;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.studyjams.js0261.checked.R;

public class ListActivity extends Activity {

    private ListView listView;
    private ArrayList list;
    private BookAdapter bookAdapter;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.listView);
        et = (EditText) findViewById(R.id.et);
        et.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        initList();
        initData();
    }

    private void initData() {
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                    list.add(et.getText().toString().trim());
                    bookAdapter.notifyDataSetChanged();
                    et.setText("请输入您想添加的内容");
                    return true;

                }
                return false;
            }

        });
        if (bookAdapter == null) {
            bookAdapter = new BookAdapter(getApplicationContext(), list);
            listView.setAdapter(bookAdapter);
        } else {
            bookAdapter.notifyDataSetChanged();
        }
    }

    private void initList() {
        if (list == null) {
            list = new ArrayList<>();
        }
    }
}

class BookAdapter extends BaseAdapter {

    private ArrayList<String> list;
    private Context cotext;

    public BookAdapter(Context cotext, ArrayList<String> list) {
        this.list = list;
        this.cotext = cotext;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(cotext, R.layout.adapter_book, null);
            holder = new ViewHolder();
            holder.cb_select = (CheckBox) convertView.findViewById(R.id.adapter_cb);
            holder.tv_content = (TextView) convertView.findViewById(R.id.adapter_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String msg = list.get(position);
        holder.tv_content.setText(msg);

        final ViewHolder finalHolder = holder;
        holder.cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("isChecked--" + isChecked + "===" + buttonView);
                if (isChecked) {
                    Toast.makeText(cotext,"恭喜你完成一个任务",Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    finalHolder.cb_select.setChecked(false);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }

    public class ViewHolder {
        public CheckBox cb_select;
        public TextView tv_content;
    }
}


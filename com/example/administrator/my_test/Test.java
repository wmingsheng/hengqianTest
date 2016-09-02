package com.example.administrator.my_test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.my_test.contacts.utils.ChAdapter;
import com.example.administrator.my_test.contacts.utils.DividerGridDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */

public class Test extends Activity implements ChAdapter.MOnItemClickListener,ChAdapter.MyOnItemLongClickListener{
//    private TextView tv;
//    private EditText et;
//    private ListView lv;
    private List<Character> mList;
//    private ArrayAdapter<String> adapter;

    private RecyclerView rw;
//    private CharAdapter mAdapter;
    private ChAdapter mAdapter;
    private TextView recycler_add;
    private TextView recycler_del;
    private int mPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.list_array_layout);

        setContentView(R.layout.recycleview_layout);

//        initListArrayView();
//        initListArrayListener();

        init();
    }

    public void init() {
        initData();
        rw = (RecyclerView) findViewById(R.id.test_recycler_view);
        recycler_add = (TextView) findViewById(R.id.recycler_add);
        recycler_del = (TextView) findViewById(R.id.recycler_del);

        //GridLayoutManager形式
//        GridLayoutManager manager = new GridLayoutManager(this,4);
//        manager.setOrientation(GridLayoutManager.HORIZONTAL);

        //StaggeredGridLayoutManager形式
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL);

        rw.setLayoutManager(manager);

//        rw.addItemDecoration(new DividerGridItemDecoration(this));
        rw.addItemDecoration(new DividerGridDecoration(this));

//        mAdapter = new CharAdapter(this,mList);
        mAdapter = new ChAdapter(this,mList);

        mAdapter.setmOnItemClickListener(this);
        mAdapter.setMyOnItemLongClickListener(this);
//        mAdapter.setYOnItemClickListener(this);
        rw.setAdapter(mAdapter);

        recycler_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addList(mPosition);

                Snackbar.make(v,"insert one ," + mPosition,Snackbar.LENGTH_SHORT).show();
            }
        });

        recycler_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int childCount = mAdapter.getItemCount();
                if (childCount > mPosition)
                   mAdapter.removeList(mPosition);
                else
                    mPosition = childCount;
//                Snackbar.make(v,"remove one ," + mPosition,Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void initData() {
        mList = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mList.add((char)i);
        }
    }

    @Override
    public void mOnItemClickListener(View view, int position) {
        mPosition = position;
        Snackbar.make(view,mPosition + "",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void myOnItemLongClickListener(View view, int position) {
        Toast.makeText(Test.this,"------- " + mList.get(position) + " -------",Toast.LENGTH_SHORT).show();
    }

    class CharAdapter extends RecyclerView.Adapter<CharAdapter.mViewHolder>{
        private Context mContext;
        private List mList;
        private YOnItemClickListener mListener;

        public CharAdapter(Context context,List list) {
            mContext = context;
            if (list == null) {
                mList = new ArrayList();
            } else {
                mList = list;
            }
        }

        public void setYOnItemClickListener(YOnItemClickListener listener) {
            mListener = listener;
        }
        @Override
        public CharAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.rect_layout,null);
            mViewHolder holder = new mViewHolder(view,mListener);
            return holder;
        }

        @Override
        public void onBindViewHolder(CharAdapter.mViewHolder holder, int position) {
            holder.tv.setText( mList.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class mViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            YOnItemClickListener yListener;
            public mViewHolder(View itemView,YOnItemClickListener listener) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.rect_title);
                yListener = listener;
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (yListener != null)
                            yListener.onItemClickListener(v,getPosition());
                    }
                });
            }
        }
    }

    public interface YOnItemClickListener {
        void onItemClickListener(View view,int position);
   }

    /*
    * ViewStub组件测试
    * */
   /* private void init() {
        setContentView(R.layout.viewstub_layout);

        View stub = findViewById(R.id.aty_viewstub_vs);
        stub.setVisibility(View.VISIBLE);               //显示ViewStub

        //此处的findViewById位布局中声明的android:InflatedId
        View viewStub = findViewById(R.id.aty_viewstub_list);
        viewStub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"this is ViewStub ...",Snackbar.LENGTH_INDEFINITE)
                        .setAction("cannel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).setActionTextColor(Color.YELLOW).show();
            }
        });

        tv = (TextView) findViewById(R.id.aty_viewstub_tv);
        Button btn = (Button) findViewById(R.id.aty_list_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Happy Birthday !");
            }
        });
    }

    *//*
    * list_array_layout布局中的控件声明
    * *//*
    private void initListArrayView() {
        et = (EditText) findViewById(R.id.aty_list_array_et);
        lv = (ListView) findViewById(R.id.aty_list_array_lv);
        mList = new ArrayList<String>();

       adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item,mList);
        lv.setAdapter(adapter);
    }

    *//*
    * list_array_layout布局：事件和数据的添加
    * *//*
    public void initListArrayListener() {
        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER ||
                            keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                        mList.add(et.getText().toString());
                        adapter.notifyDataSetChanged();       //adapter的刷新
                        et.setText("");
                    }
                }
                return  false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int index = position;
                AlertDialog dialog = new AlertDialog.Builder(Test.this).setMessage("您确定要删除此项目吗.......")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mList.remove(index);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
            }
        });
    }*/
}

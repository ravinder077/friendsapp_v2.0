/*


package com.example.ravinder077.friendsapp;

        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Bitmap;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.design.widget.NavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import android.widget.PopupWindow;
        import android.widget.RelativeLayout;
        import android.widget.Toast;


        import com.wangjie.androidbucket.utils.ABTextUtil;
        import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
        import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
        import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
        import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
        import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.concurrent.ExecutionException;

        import static android.content.Context.LAYOUT_INFLATER_SERVICE;
        import static android.content.Context.MODE_PRIVATE;

*/
/**
 * Created by ravinder077 on 29-06-2017.
 *//*


public class TabB extends Fragment implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener, NavigationView.OnNavigationItemSelectedListener
{

    private List<ChatData> postlist = new ArrayList<>();
    private RecyclerView MyRecyclerView;
    private ChatCardAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    String jsonphoto;
    String mno=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<ChatData> al = new ArrayList<ChatData>();

        // ChatData cdg=new ChatData();

        SQLiteDatabase mydata=getActivity().openOrCreateDatabase("DM",MODE_PRIVATE,null);
        Cursor resultSet = mydata.rawQuery("Select * from new_user", null);
        if(resultSet.getCount()>0) {
            resultSet.moveToFirst();
            //String uname = resultSet.getString(0);
            mno = resultSet.getString(1);
        }

        OtpGen otpgen =new OtpGen();
        otpgen.execute("http://omtii.com/mile/text.php?mobno="+mno);
        try {
            System.err.println("Photo cc" + otpgen.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        try {
            jsonphoto= otpgen.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        try {
            ArrayList<ChatData> photono=  jsonToMap(jsonphoto);
            initializeList(photono);
        } catch (JSONException e) {
            e.printStackTrace();
        }





    }


    //rajan add floating action button starts
    private RapidFloatingActionHelper rfabHelper;

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();
        item.getLabel();

        int positionIndex = 6 - position;
        //Toast.makeText(getActivity(),  item.getLabel(), Toast.LENGTH_SHORT).show();
        if(item.getLabel().toString().equals("New Page")) {
            Intent i = new Intent(getContext(), ProfilePage.class);
            startActivity(i);
        }

        else if(item.getLabel().toString().equals("New Group")) {
            Intent intent = new Intent(getContext(), GroupCreate.class);
            startActivity(intent);
        }

        else if(item.getLabel().toString().equals("New Chat")) {
            Intent intent = new Intent(getContext(), NewChatFriend.class);
            startActivity(intent);
        }

    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        rfabHelper.toggleContent();

        // Toast.makeText(getActivity(), item.getLabel().toString(), Toast.LENGTH_SHORT).show();
        int positionIndex = 6 - position;

        if(item.getLabel().toString().equals("New Page")) {
            Intent i = new Intent(getContext(), ProfilePage.class);
            startActivity(i);
        }
        else if(item.getLabel().toString().equals("New Group")) {
            Intent intent = new Intent(getContext(), GroupCreate.class);
            startActivity(intent);
        }
        else if(item.getLabel().toString().equals("New Chat")) {
            Intent intent = new Intent(getContext(), NewChatFriend.class);
            startActivity(intent);
        }

        // Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rfab, container, false);

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this.getActivity());

        MyRecyclerView.setLayoutManager(mLayoutManager);
        // preparePostData();
        adapter = new ChatCardAdapter(postlist);*/
/**//*

        MyRecyclerView.setAdapter(adapter);

        MyRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(getContext());

                rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
                List<RFACLabelItem> items = new ArrayList<>();
                items.add(new RFACLabelItem<Integer>()
                        .setLabel("New Chat")
                        .setResId(R.drawable.chat)
                        .setIconNormalColor(0xFFffb022)
                        .setIconPressedColor(0xFFffb022)
                        .setWrapper(0)
                );
                items.add(new RFACLabelItem<Integer>()
                        .setLabel("New Group")
                        .setResId(R.drawable.group)
                        .setIconNormalColor(0xFF0ee5ff)
                        .setIconPressedColor(0xFF0ee5ff)
                        .setWrapper(1)
                );
                items.add(new RFACLabelItem<Integer>()
                        .setLabel("New Page")
                        .setResId(R.drawable.page)
                        .setIconNormalColor(0xFF55F207)
                        .setIconPressedColor(0xFF3CE60E)
                        .setWrapper(2)
                );
                rfaContent
                        .setItems(items)
                        .setIconShadowRadius(ABTextUtil.dip2px(view.getContext(), 5))
                        .setIconShadowColor(0xff888888)
                        .setIconShadowDy(ABTextUtil.dip2px(view.getContext(), 5));
                rfabHelper = new RapidFloatingActionHelper(
                        view.getContext(),
                        (RapidFloatingActionLayout) view.findViewById(R.id.activity_main_rfal),
                        (RapidFloatingActionButton) view.findViewById(R.id.activity_main_rfab),
                        rfaContent
                ).build();
                return view;
            }
        }
    }

//rajan add floating action button ends


    public ArrayList<ChatData> jsonToMap(String jsonStr) throws JSONException {


        HashMap<String, String> contact = new HashMap<>();

        ArrayList<ChatData> al = new ArrayList<ChatData>();

        if (jsonStr != null) {

            System.err.print("josn strin is");

            System.err.print(jsonStr);

            System.err.print("josn strin ends");


            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("tasks");

                // looping through All Contacts
                JSONArray c = jsonObj.getJSONArray("tasks");
                for (int i = 0; i < c.length(); i++) {
                    JSONObject obj = c.getJSONObject(i);

                    String profileimg = obj.getString("profileimg");
                    String profilename = obj.getString("profilename");
                    String pagename = obj.getString("pagename");
                    String pagepic = obj.getString("pagepic");
                    String groupname = obj.getString("groupname");


                    ChatData cc = new ChatData();

                    cc.setProfilepic(profileimg);
                    cc.setProfilename(profilename);
                    cc.setPagename(pagename);
                    cc.setPagepic(pagepic);
                    cc.setGroupname(groupname);

                    GetBitmapfromUrlThread getBitmapfromUrlThread = new GetBitmapfromUrlThread();
                    getBitmapfromUrlThread.execute(profileimg);
                    Bitmap bb = getBitmapfromUrlThread.get();


                    cc.setBitprofilepic(bb);


                    GetBitmapProfilefromUrlThread getBitmapProfilefromUrlThread = new GetBitmapProfilefromUrlThread();

                    getBitmapProfilefromUrlThread.execute(profileimg);
                    Bitmap ccc = getBitmapProfilefromUrlThread.get();


                    cc.setBitprofilepic(ccc);

                    al.add(cc);


                }


            } catch (Exception e) {
                e.printStackTrace();


            }
        }

        return al;
    }

    public void initializeList(ArrayList<ChatData> al) {
        postlist.clear();

        for (ChatData cd : al) {


            ChatData item = new ChatData();


            item.setBitprofilepic(cd.getBitprofilepic());
            item.setProfilepic(cd.getProfilepic());
            item.setGroupname(cd.getGroupname());
            item.setBitpagepic(cd.getBitpagepic());
            item.setPagename(cd.getPagename());
            item.setPagepic(cd.getPagepic());
            item.setProfilename(cd.getProfilename());

            //item.setPostVideo(cd.getPostVideo());

            postlist.add(item);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}*/

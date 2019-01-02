package hongzicong.saltedfish.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.utils.SharedPreferencesUtils;
import hongzicong.saltedfish.utils.UIUtils;

public class NewFlagActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_prob)
    EditText probEditText;

    @BindView(R.id.edit_text_likely)
    EditText likelyEditText;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_new);
        ButterKnife.bind(this);

        initToolBar();

    }


    private boolean addInSharedPreferences(){
        String probText = probEditText.getText().toString();
        String likeText = likelyEditText.getText().toString();

        if(probText.equals("") || likeText.equals("")){
            Toast.makeText(UIUtils.getContext(), "空的Flag怎么能立起来呢？", Toast.LENGTH_SHORT).show();
            return false;
        }

        SharedPreferencesUtils.getInstance().putString("prob_flag", probText);

        SharedPreferencesUtils.getInstance().putString("likely_flag", likeText);

        return true;
    }

    protected void initToolBar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        this.setResult(3,intent);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case android.R.id.home:
            case R.id.action_no:
                intent=new Intent();
                this.setResult(3,intent);
                finish();
                return true;
            case R.id.action_ok:
                if(addInSharedPreferences()){
                    intent=new Intent();
                    this.setResult(2,intent);
                    SharedPreferencesUtils.getInstance().putBoolean("has_flag", true);
                    finish();
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_add,menu);
        return true;
    }
}

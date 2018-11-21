package hongzicong.saltedfish.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.utils.SharedPreferencesUtils;

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
        setContentView(R.layout.activity_new_flag);
        ButterKnife.bind(this);

        initToolBar();

    }

    private boolean isLegal(){
        return true;
    }

    private boolean addInDB(){
        if(isLegal()){
            // TODO
            String probText = probEditText.getText().toString();
            String likeText = likelyEditText.getText().toString();
            String probTextArr[] = probText.split("\n");
            String likeTextArr[] = likeText.split("\n");
            ArrayList<String> probArr = new ArrayList<>();
            ArrayList<String> likeArr = new ArrayList<>();
            for(String prob:probTextArr){
                //probArr.add(prob)
            }
            for(String likely:likeTextArr){

            }

            return true;
        }
        return false;
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
                if(addInDB()){
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

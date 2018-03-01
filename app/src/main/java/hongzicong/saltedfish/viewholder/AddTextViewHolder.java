package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;

/**
 * Created by Dv00 on 2018/1/4.
 */

public class AddTextViewHolder extends RecyclerView.ViewHolder {

    private String mText;
    private String mTextHint;

    @BindView(R.id.text_view)
    TextView textView;

    @BindView(R.id.edit_text)
    EditText editText;

    public AddTextViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup,String text,String textHint){
        super(layoutInflater.inflate(R.layout.item_edit_text,viewGroup,false));
        ButterKnife.bind(this,itemView);
        mText=text;
        mTextHint=textHint;
        setAllText();
    }

    private void setAllText(){
        textView.setText(mText);
        editText.setHint(mTextHint);
    }

    public String getText(){
        return editText.getText().toString();
    }

}

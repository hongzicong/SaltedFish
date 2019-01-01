package hongzicong.saltedfish.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hongzicong.saltedfish.R;
import hongzicong.saltedfish.utils.SharedPreferencesUtils;
import hongzicong.saltedfish.utils.UIUtils;

import static hongzicong.saltedfish.view.TableView.BLUE;
import static hongzicong.saltedfish.view.TableView.PURPLE;
import static hongzicong.saltedfish.view.TableView.RED;

public class SettingColorViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view)
    TextView textView;

    @BindView(R.id.color_view)
    View colorView;

    public SettingColorViewHolder(View itemView, String text){
        super(itemView);
        ButterKnife.bind(this,itemView);
        textView.setText(text);
        int style = SharedPreferencesUtils.getInstance().getInt("style", 0);
        switch (style){
            case BLUE:
                setColor(R.color.fill_2_blue);
                break;
            case RED:
                setColor(R.color.fill_2_red);
                break;
            case PURPLE:
                setColor(R.color.fill_2_purple);
                break;
        }
    }

    public void setColor(int color){
        colorView.setBackground(UIUtils.getResources().getDrawable(color));
    }

}

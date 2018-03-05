package hongzicong.saltedfish.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import hongzicong.saltedfish.R;

public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE="date";

    private FragmentInteraction fragmentInteraction;

    public interface FragmentInteraction{
        void changeDate(long time);
    }

    public void setFragmentInteraction(FragmentInteraction fragmentInteraction){
        this.fragmentInteraction=fragmentInteraction;
    }

    public static DatePickerFragment newInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable(ARG_DATE,date);

        DatePickerFragment fragment=new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date=(Date)getArguments().getSerializable(ARG_DATE);

        final Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);

        View v= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);
        final DatePicker datePicker=ButterKnife.findById(v,R.id.dialog_date_picker);
        datePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Calendar calendar=Calendar.getInstance();
                        calendar.set(Calendar.YEAR,datePicker.getYear());
                        calendar.set(Calendar.MONTH,datePicker.getMonth());
                        calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                        fragmentInteraction.changeDate(calendar.getTimeInMillis());
                    }
                })
                .create();
    }
}

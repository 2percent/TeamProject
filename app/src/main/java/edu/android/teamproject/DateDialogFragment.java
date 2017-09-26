package edu.android.teamproject;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateDialogFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "edu.android";
    private static Fragment frag;

    public interface DateSelectListener {
        void dateSelected(int year, int month, int dayOfMonth);
    }

    private DateSelectListener listener;

    public DateDialogFragment() {
        // Required empty public constructor
    }
    public DateDialogFragment(Fragment frag){
        this.frag = frag;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof DateSelectListener) {
            listener = (DateSelectListener) context;
        }else if(frag instanceof DateSelectListener){
            listener = (DateSelectListener) frag;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 현재 시간 정보
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR); // 현재 연도
        int month = cal.get(Calendar.MONTH); // 현재 월
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH); // 현재 날짜

        DatePickerDialog dlg = new DatePickerDialog(getContext(), // context정보
                this, // DatePicker에서 날짜를 선택했을 때 호출 될 리스너(기능)
                year, month, dayOfMonth); // 연,월,일

        return dlg;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        if (listener != null) {
            listener.dateSelected(year, month, dayOfMonth);
        }
    }
}

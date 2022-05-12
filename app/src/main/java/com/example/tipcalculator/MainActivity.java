package com.example.tipcalculator;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText edtSubtotal;
    private TextView txtSeekBar;
    private TextView txtTotTip;
    private TextView txtTotal;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSubtotal = (EditText) findViewById(R.id.subtotal);
        txtSeekBar = (TextView) findViewById(R.id.txtSeekBar);
        txtTotTip = (TextView) findViewById(R.id.txtTotTip);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        // edtSubtotal.getBackground().setColorFilter(Color.parseColor("#ADE7F6"), PorterDuff.Mode.SRC_ATOP);
        edtSubtotal.setShowSoftInputOnFocus(false);

        edtSubtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(edtSubtotal.getText().toString())) {
                    edtSubtotal.setText("");
                }

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        txtSeekBar.setText(Integer.toString(progress) + "%");
                        double subtotal;
                        try {
                            subtotal = Double.parseDouble(edtSubtotal.getText().toString());
                        } catch (Exception e) {
                            subtotal = 0.00;
                        }
                        double decimalTip = progress/100.0;
                        double dollarsTip = decimalTip*subtotal;
                        double total = subtotal + dollarsTip;
                        txtTotTip.setText("$" + Double.toString(dollarsTip));
                        txtTotal. setText("$" + Double.toString(total));

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });
    }

    private void updateText(String addStr) {
        String prevStr = edtSubtotal.getText().toString();

        // gets the whatever text is in calcDisplay, relative to cursor position
        // e.g. if cursor is placed in the middle of the text, leftStr takes the left side of the text
        // rightStr takes the right side of the text where the cursor is located\
        int cursorPos = edtSubtotal.getSelectionStart();
        String leftStr = prevStr.substring(0, cursorPos);
        String rightStr = prevStr.substring(cursorPos);

        if (getString(R.string.display).equals(edtSubtotal.getText().toString())) {
            edtSubtotal.setText(addStr);
            // change cursor position once text has been added
            edtSubtotal.setSelection(cursorPos + 1);
        } else {
            edtSubtotal.setText(String.format("%s%s%s", leftStr, addStr, rightStr));
            // change cursor position once text has been added
            edtSubtotal.setSelection(cursorPos + 1);
        }

    }

    public void zeroBtn(View view){ updateText("0"); }
    public void oneBtn(View view){
        updateText("1");
    }
    public void twoBtn(View view){
        updateText("2");
    }
    public void threeBtn(View view){
        updateText("3");
    }
    public void fourBtn(View view){
        updateText("4");
    }
    public void fiveBtn(View view){
        updateText("5");
    }
    public void sixBtn(View view){
        updateText("6");
    }
    public void sevenBtn(View view){
        updateText("7");
    }
    public void eightBtn(View view){
        updateText("8");
    }
    public void nineBtn(View view){
        updateText("9");
    }
    public void dotBtn(View view){
        updateText(".");
    }
    public void clearBtn(View view){
        edtSubtotal.setText("");
    }

}
package com.example.fuels;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private NumberFormat n = NumberFormat.getCurrencyInstance();
    private SeekBar gasolinaSeekBar;
    private SeekBar etanolSeekBar;
    private TextInputLayout choice;
    private EditText choiceEditText;
    private ImageView image;
    private TextView valueGasolina;
    private TextView valueEthanol;
    private int gasolina;
    private int etanol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gasolinaSeekBar = findViewById(R.id.gasolineSeekBar);
        etanolSeekBar = findViewById(R.id.etanolSeekBar);
        choiceEditText = findViewById(R.id.choiceEditText);
        valueEthanol = findViewById(R.id.valueEthanol);
        valueGasolina = findViewById(R.id.valueGasolina);
        image = findViewById(R.id.image);
        gasolina=gasolinaSeekBar.getProgress();
        etanol=etanolSeekBar.getProgress();
        calculation(gasolina,etanol);
        valueGasolina.setText(n.format(gasolina));
        valueEthanol.setText(n.format(etanol));
        gasolinaSeekBar.setOnSeekBarChangeListener(new gasolinaSeekWatcher());
        etanolSeekBar.setOnSeekBarChangeListener(new etanolSeekWatcher());
    }
    private class gasolinaSeekWatcher implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            gasolina = progress;
            calculation(progress,etanol);

            valueGasolina.setText(n.format(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
    private class etanolSeekWatcher implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            etanol = progress;
            calculation(gasolina,progress);

            valueEthanol.setText(n.format(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
    public void calculation(int gasolina,int etanol){
        if(gasolina<=0){
            gasolina = 1;
        }
        if(etanol<=0){
            etanol =1;
        }
        float result = etanol/(float)gasolina;
        if(result>0.7){
            choiceEditText.setText(R.string.gasolina);
            image.setImageResource(R.drawable.gasolina);
            image.setContentDescription(String.valueOf(R.string.gasolina));
        }else{
            choiceEditText.setText(R.string.etanol);
            image.setImageResource(R.drawable.etanol);
            image.setContentDescription(String.valueOf(R.string.etanol));
        }
    }
}
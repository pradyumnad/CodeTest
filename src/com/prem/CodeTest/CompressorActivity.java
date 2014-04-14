package com.prem.CodeTest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;

public class CompressorActivity extends Activity {

  private EditText inputText;
  private Button compressButton;
  private TextView compressedText;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.compressor);
    getWidgets();
    applyActionToButton();
  }

  private void getWidgets() {
    inputText = (EditText) findViewById(R.id.text_input);
    compressButton = (Button) findViewById(R.id.button_compressor);
    compressedText = (TextView) findViewById(R.id.text_compressed);
  }


  private void applyActionToButton() {

    compressButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String inputString = inputText.getText().toString();
        if (inputString == null || inputString.length() == 0) {
          inputText.setError("Please Enter Input");
        } else {
          String compressedString = compressText(inputString);
          compressedText.setVisibility(View.VISIBLE);
          compressedText.setText(compressedString);
        }
      }
    });
  }

  private String compressText(String inputString) {

    String[] words = inputString.split(" ");
    String compressedString = new String(inputString);

    for (String word : words) {
      if (JSONHelper.jsonObject.has(word)) {
        try {
          compressedString = compressedString.replace(word, JSONHelper.jsonObject.getString(word));
        } catch (JSONException e) {
          Log.e("Json Exception:", e.getMessage());
        }
      }
    }
    return compressedString;
  }
}

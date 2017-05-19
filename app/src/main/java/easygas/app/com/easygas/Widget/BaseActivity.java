package easygas.app.com.easygas.Widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import easygas.app.com.easygas.Utils.Utils;

import static easygas.app.com.easygas.Utils.Constant.KEY_USERID;

public class BaseActivity extends Activity {

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
    }

    protected void toast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int resId) {
        toast(this.getResources().getText(resId));
    }

    protected void startActivity(Class klass) {
        startActivity(new Intent(this, klass));
    }

    protected void startActivityData(Class klass, HashMap<String, String> hash) {
        Intent in = new Intent(this, klass);
        for (Map.Entry<String, String> entry : hash.entrySet()) {
            in.putExtra(entry.getKey(), entry.getValue());
        }
        startActivity(in);

    }

    protected String getText(EditText eTxt) {
        return eTxt == null ? "" : eTxt.getText().toString().trim();
    }

    protected boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    protected void write(String key, String val) {
        Utils.WriteSharePrefrence(this, key, val);
    }

    protected String read(String key) {
        return Utils.ReadSharePrefrence(this, key);
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {

            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean isLoggedIn() {
        if (read(KEY_USERID).equalsIgnoreCase("")) {
            return false;
        } else {
            return true;
        }

    }
}

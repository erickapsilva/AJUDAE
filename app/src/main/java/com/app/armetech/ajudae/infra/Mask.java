package com.app.armetech.ajudae.infra;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public abstract class Mask {
    public static TextWatcher insert(final String mask, final EditText et) {
        return new TextWatcher() {
            boolean isUpdating;
            String oldTxt = "";
            public void onTextChanged(
                    CharSequence s, int start, int before,int count) {
                String str = Mask.unmask(s.toString());
                String maskCurrent = "";
                if (isUpdating) {
                    oldTxt = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > oldTxt.length()) {
                        maskCurrent += m;
                        continue;
                    }
                    try {
                        maskCurrent += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                et.setText(maskCurrent);
                et.setSelection(maskCurrent.length());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {}
        };
    }
    private static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }
}

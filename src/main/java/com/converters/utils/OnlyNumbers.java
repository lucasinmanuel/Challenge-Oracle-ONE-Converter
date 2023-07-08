package com.converters.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class OnlyNumbers extends PlainDocument {
    @Override
    public void insertString(int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null)
            return;

        StringBuilder builder = new StringBuilder(string);
        for (int i = builder.length() - 1; i >= 0; i--) {
            int cp = builder.codePointAt(i);
            if (!Character.isDigit(cp) && cp != '.' && cp != '-')
                builder.deleteCharAt(i);
        }

        super.insertString(offset, builder.toString(), attr);
    }
}
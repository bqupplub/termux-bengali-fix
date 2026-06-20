package com.termux.terminal;

import android.icu.text.BreakIterator;

public class GraphemeClusterHelper {
    private static final ThreadLocal<BreakIterator> CHAR_BREAK_ITERATOR = new ThreadLocal<BreakIterator>() {
        @Override
        protected BreakIterator initialValue() {
            return BreakIterator.getCharacterInstance();
        }
    };

    public static boolean isGraphemeCluster(char[] text, int offset, int length, int codePoint) {
        if (length == 0) return false;
        String str = new String(text, offset, length) + new String(Character.toChars(codePoint));
        BreakIterator it = CHAR_BREAK_ITERATOR.get();
        it.setText(str);
        int next = it.next();
        return next == BreakIterator.DONE || next == str.length();
    }
}

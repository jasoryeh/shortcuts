package tk.jasonho.shortcuts.everything.chat;

import lombok.Getter;
import lombok.Setter;

public class Colors {
    @Getter
    @Setter
    private static char COLOR_CODE = '&';

    @Getter
    @Setter
    private static char MC_COLOR_CODE = '§';

    public static String translate(String txt){
        String newText = "";
        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);
            if (c == COLOR_CODE) {
                c = MC_COLOR_CODE;
            }
            newText += c;
        }
        return newText;
    }
}

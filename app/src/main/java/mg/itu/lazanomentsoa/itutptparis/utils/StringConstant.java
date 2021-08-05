package mg.itu.lazanomentsoa.itutptparis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringConstant {
    public static String createUniqueTag() {
        return "t" + (new Date().getTime());
    }
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat dateFormatToSend = new SimpleDateFormat("yyyy-MM-dd");
}

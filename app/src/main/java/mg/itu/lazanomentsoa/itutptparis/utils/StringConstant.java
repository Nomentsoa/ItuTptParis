package mg.itu.lazanomentsoa.itutptparis.utils;

import java.util.Date;

public class StringConstant {
    public static String createUniqueTag() {
        return "t" + (new Date().getTime());
    }
}

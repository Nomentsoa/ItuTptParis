package mg.itu.lazanomentsoa.itutptparis.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringConstant {
    public static String createUniqueTag() {
        return "t" + (new Date().getTime());
    }
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat dateFormatToSend = new SimpleDateFormat("yyyy-MM-dd");

    public static String getThousantNumberByString(String prix) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.FRENCH);
        formatter = new DecimalFormat("##,###.## ");
        String prixFormated = formatter.format(Double.parseDouble(prix.replaceAll("\\s", "")));
        prixFormated = prixFormated.replace(",", " ");
        return prixFormated;
    }
}

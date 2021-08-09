package mg.itu.lazanomentsoa.itutptparis.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static final Pattern VALIDE_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmailValide(String emailStr) {
        Matcher matcher = VALIDE_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}

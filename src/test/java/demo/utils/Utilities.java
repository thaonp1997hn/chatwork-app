package demo.utils;

import net.serenitybdd.core.Serenity;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Strings;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Utilities {
    public static boolean isCurrentPlatformAndroid() {
        return DataUtils.getValueConf("appium.platformName").equalsIgnoreCase("android");
    }

    public static String getCurrentLanguage() {
        String currentLanguage = Serenity.sessionVariableCalled("currentLanguage");
        if (Strings.isNullOrEmpty(currentLanguage))
            return DataUtils.getValueConf("serenity.lang");
        else
            return currentLanguage;
    }

    public static String getPropertiesLangValue(String ws, String key) {
        Properties props = new Properties();
        String filePath = "language" + File.separator + ws + File.separator + getCurrentLanguage().toLowerCase() + ".properties";
        try {
            InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(filePath);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            props.load(isr);
        } catch (Exception exception) {
            System.out.println("Error file path: " + exception);
        }
        return props.getProperty(key);
    }

    public static String processAccountNumberFormat(String account, String length) {
        return account.replaceAll("(.{" + length + "})", "$0 ").trim();
    }
}
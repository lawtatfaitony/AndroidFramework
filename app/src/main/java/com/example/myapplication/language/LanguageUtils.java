package com.example.myapplication.language;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.myapplication.R;
import com.example.myapplication.Store;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by Administrator on 2019/3/4.
 * 国际化适配工具类
 */

public class LanguageUtils {
    // 在這裡處理按鈕點擊事件
    public static final String[] locals = {"zh-CN", "en","zh-HK","zh-HK"}; //最後一個是默認語言
    private static final String TAG = "LanguageUtils";
    private static Context mContext ;
    //call this function to update application language.
    public static void initLanguage(Context context) {
        mContext = context;
        //獲取語言標識的邏輯業務
        //優先使用用戶設置的（from preferences），如沒有則獲取系統的
        String storeLanguageName = Store.getLanguageLocal(mContext); //如果沒有設置的情況或保存系統的
        Log.e(TAG, "initLanguage:  ----  >  storeLanguageName = "+ storeLanguageName);
        Resources resources = mContext.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(storeLanguageName);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());//更新配置
    }

    /**
     * 從文件獲取語言標識（棄用此功能）language.txt（文件內容：en-US）已刪除此文件 無意義 應直接從系統獲取
     * @param context
     * @return
     */
    private static String readLanguageName(Context context) {
        mContext = context;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            AssetManager am= mContext.getAssets();
            InputStream is=am.open("language.txt");
            String code=getCode(is);
            is=am.open("language.txt");  //已刪除此文件 無意義 應直接從系統獲取
            BufferedReader br=new BufferedReader(new InputStreamReader(is,code));
            String line=br.readLine();
            int i=0;
            if(line==null)
                return Store.getLanguageLocal(mContext);

            while(line!=null){
                stringBuilder.append(line+"\n");
                line=br.readLine();
                i++;
                if(i>20){  //可存儲20個國家的語言在language.txt(其他地方的邏輯要對應增加這個)
                    break;
                }
            }
            return stringBuilder.toString().trim();

        } catch (IOException e) {
            e.printStackTrace();
            return  getCurrentLanguage();
        }
    }

    public static String getCode(InputStream is){
        try {
            BufferedInputStream bin = new BufferedInputStream(is);
            int p;

            p = (bin.read() << 8) + bin.read();

            String code = null;

            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                default:
                    code = "GBK";
            }
            is.close();
            return code;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前支持语言，不支持默认英文  TODO  新增华为
     * @return
     */
    public static String getCurrentLanguage() {
        try {
            /*Resources resources = context.getResources();
            Configuration configuration = resources.getConfiguration();*/
            Locale locale = getSysPreferredLocale();
            Log.e(TAG, "getCurrentLanguage:locale  ----  >  "+locale );
            String language = locale.getLanguage();
            Log.e(TAG, "getCurrentLanguage:  ----  >  "+locale.getCountry() );
            Log.e(TAG, "getCurrentLanguage:  获取到语言 "+language  );
            if(language.equals("en")){
                return "en-US";
            }else if(language.equals("zh")){
                if(locale.getCountry().toUpperCase().equals("TW")){
                    //return "zh-TW";  // 都是返回 zh-HK
                    return "zh-HK";
                }else if (locale.getCountry().equals("HK")||locale.toString().contains("#Hant"))
                {
                    return "zh-HK";
                }else{
                    return "zh-CN";
                }
            }else if(language.equals("ko")){
                return "ko-KR";
            }else{
                return "en-US";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "en-US";
        }
    }
    /**
     * 获取系统首选语言
     *
     * 注意：该方法获取的是用户实际设置的不经API调整的系统首选语言
     *
     * @return
     */
    public static Locale getSysPreferredLocale() {
        Locale locale;
        //7.0以下直接获取系统默认语言
        if (Build.VERSION.SDK_INT < 24) {
            // 等同于context.getResources().getConfiguration().locale;
            locale = Locale.getDefault();
            // 7.0以上获取系统首选语言
        } else {
            /*
             * 以下两种方法等价，都是获取经API调整过的系统语言列表（可能与用户实际设置的不同）
             * 1.context.getResources().getConfiguration().getLocales()
             * 2.LocaleList.getAdjustedDefault()
             */
            // 获取用户实际设置的语言列表
            locale = LocaleList.getDefault().get(0);
        }
        return locale;
    }

}

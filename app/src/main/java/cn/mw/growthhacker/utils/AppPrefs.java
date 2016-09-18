package cn.mw.growthhacker.utils;

import android.content.Context;


import java.io.IOException;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.domain.O2ODetailData;
import cn.mw.growthhacker.domain.ShopDetailData;
import cn.mw.growthhacker.domain.TravelDetailData;

/**
 * Created by Tony Shen on 15/12/14.
 */
public class AppPrefs extends BasePrefs {

    private static final String PREFS_NAME = "AppPrefs";

    private static final String LAST_VERSION = "last_version";
    private static final String GUIDE_TOUR = "guide_tour";
    private static final String GUIDE_EBUSINESS = "guide_ebusiness";
    private static final String CLIENT_ID = "clientid";

    private AppPrefs(Context context) {
        super(context, PREFS_NAME);
    }

    public static AppPrefs get(Context context) {
        if (context == null) {
            return null;
        } else {
            return new AppPrefs(context.getApplicationContext());
        }
    }

    public String getCid() {
        return getString(CLIENT_ID, null);
    }

    public void setCid(String v) {
        putString(CLIENT_ID, v);
    }

    public String getLastVersion() {
        return getString(LAST_VERSION, null);
    }

    public void setLastVersion(String v) {
        putString(LAST_VERSION, v);
    }

    public boolean getGuideTour() {
        return getBoolean(GUIDE_TOUR, false);
    }

    public void setGuideTour(boolean flag) {
        putBoolean(GUIDE_TOUR, flag);
    }

    public boolean getGuideEbusiness() {
        return getBoolean(GUIDE_EBUSINESS, false);
    }

    public void setGuideEbusiness(boolean flag) {
        putBoolean(GUIDE_EBUSINESS, flag);
    }

    public void saveJson(String key, String value) {
        putString(key, value);
    }

//    public BusinessList getBusiness() {
//        BusinessList list = new BusinessList();
//        String business = getString(Config.businessList, "");
//        if (!TextUtils.isEmpty(business)) {
//            try {
//                list = RestUtil.parseAs(BusinessList.class, business);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//
//    public NewsList getNewsList() {
//        NewsList list = new NewsList();
//        String business = getString(Config.newsList, "");
//        if (!TextUtils.isEmpty(business)) {
//            try {
//                list = RestUtil.parseAs(NewsList.class, business);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//
//    public O2OList getO2OList() {
//        O2OList list = new O2OList();
//        String business = getString(Config.o2oList, "");
//        if (isOK(business)) {
//            try {
//                list = RestUtil.parseAs(O2OList.class, business);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }
//
//    public ArrayList<Pic> getPicList() {
//        ArrayList<Pic> list = new ArrayList<Pic>();
//        String business = getString(Config.picList, "");
//        if (isOK(business)) {
//            try {
//                list = RestUtil.parseArray(Pic.class, business);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }

//    public TravelList getTravelList() {
//        TravelList list = new TravelList();
//        String business = getString(Config.travelList, "");
//        if (isOK(business)) {
//            try {
//                list = RestUtil.parseAs(TravelList.class, business);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return list;
//    }

//
    public ShopDetailData getShopDetail() {
        ShopDetailData detail = new ShopDetailData();
        String shopDetail = getString(Config.shopDetail, "");

        if (isOK(shopDetail)) {
            try {
                detail = RestUtil.parseAs(ShopDetailData.class, shopDetail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return detail;
    }
//
    public O2ODetailData getO2ODetail() {
        O2ODetailData detail = new O2ODetailData();
        String o2oDetail = getString(Config.o2oDetail, "");
        if (isOK(o2oDetail)) {
            try {
                detail = RestUtil.parseAs(O2ODetailData.class, o2oDetail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return detail;
    }

    public TravelDetailData getTravelDetail() {
        TravelDetailData detail = new TravelDetailData();
        String travelDetail = getString(Config.travelDetail, "");
        if (isOK(travelDetail)) {
            try {
                detail = RestUtil.parseAs(TravelDetailData.class, travelDetail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return detail;
    }

    private boolean isOK(String httpResponse) {

        return httpResponse != null && (httpResponse.startsWith("{") || httpResponse.startsWith("["));
    }

}

package cn.mw.growthhacker.domain;

import java.util.ArrayList;
import java.util.List;

import cn.mw.growthhacker.Config;

/**
 * @author aaron
 * @date 16/3/3
 */
public class HomeListData {
    public List<ListContent> list;


    public String[] images = {"http://7xql2z.com1.z0.glb.clouddn.com/growthhacker_home_banner1.png",
            "http://7xql2z.com1.z0.glb.clouddn.com/growthhacker_home_banner2.png",
            "http://7xql2z.com1.z0.glb.clouddn.com/growthhacker_home_banner3.png",
            "http://7xql2z.com1.z0.glb.clouddn.com/growthhacker_home_banner4.png"
    };

    public String[] mws = {Config.MW_1, Config.MW_2, Config.MW_3, Config.MW_4};

    public static class ListContent {
        public String title;
        public String desc;
        public String mwKey;
    }

    public HomeListData builder() {
        list = new ArrayList<>();

        ListContent listContent0 = new ListContent();
        list.add(listContent0);


        ListContent listContent = new ListContent();
        listContent.title = "睡不着怎么办？";
        listContent.desc = "来自[过日子]";
        listContent.mwKey = Config.MW_5;
        list.add(listContent);

        ListContent listContent1 = new ListContent();
        listContent1.title = "风尚城市名片|深圳CBD百人时尚潮流盛会";
        listContent1.desc = "来自[红毯]";
        listContent1.mwKey = Config.MW_6;
        list.add(listContent1);

        ListContent listContent2 = new ListContent();
        listContent2.title = "去应该必buy的小众包包";
        listContent2.desc = "来自[迷橙]";
        listContent2.mwKey = Config.MW_7;
        list.add(listContent2);

        ListContent listContent3 = new ListContent();
        listContent3.title = "打开美人相机拍一张美美的照片吧！";
        listContent3.desc = "来自[美人相机]";
        listContent3.mwKey = Config.MW_8;
        list.add(listContent3);

        ListContent listContent4 = new ListContent();
        listContent4.title = "为您的好友选一款Beats耳机作为礼物吧！";
        listContent4.desc = "来自[心意点点]";
        listContent4.mwKey = Config.MW_9;
        list.add(listContent4);

        ListContent listContent5 = new ListContent();
        listContent5.title = "App不想成为创业困兽？那就善做杠杆，疯狂连接吧！";
        listContent5.desc = "来自[笔记侠]";
        listContent5.mwKey = Config.MW_10;
        list.add(listContent5);
        return this;
    }

}

package cn.mw.growthhacker.domain;

import java.util.ArrayList;
import java.util.List;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.R;

/**
 * @author aaron
 * @date 16/3/3
 */
public class FindListData {
    public List<ListContent> list;
    public String[] imgUrls = {"http://7xql2z.com1.z0.glb.clouddn.com/growthhacker_home_banner1.png",
            "http://7xql2z.com1.z0.glb.clouddn.com/growthhacker_home_banner2.png",
            "http://7xql2z.com1.z0.glb.clouddn.com/growthhacker_home_banner3.png",
            "http://7xql2z.com1.z0.glb.clouddn.com/growthhacker_home_banner4.png"
    };

    public static class ListContent {
        public int imgRes;
        public String imgUrl;
        public String mwKey;
    }

    public FindListData builder(){
        list = new ArrayList<>();
        ListContent listContent = new ListContent();
        listContent.imgRes = R.drawable.default_img;
        listContent.mwKey = Config.MW_1;
        listContent.imgUrl = imgUrls[0];
        list.add(listContent);

        ListContent listContent1 = new ListContent();
        listContent1.imgRes = R.drawable.default_img;
        listContent1.mwKey = Config.MW_1;
        listContent1.imgUrl = imgUrls[1];
        list.add(listContent1);

        ListContent listContent2 = new ListContent();
        listContent2.imgRes = R.drawable.default_img;
        listContent2.mwKey = Config.MW_1;
        listContent2.imgUrl = imgUrls[2];
        list.add(listContent2);

        ListContent listContent3 = new ListContent();
        listContent3.imgRes = R.drawable.default_img;
        listContent3.mwKey = Config.MW_1;
        listContent3.imgUrl = imgUrls[3];
        list.add(listContent3);

        ListContent listContent4 = new ListContent();
        listContent4.imgRes = R.drawable.default_img;
        listContent4.mwKey = Config.MW_1;
        listContent4.imgUrl = imgUrls[0];
        list.add(listContent4);

        ListContent listContent5 = new ListContent();
        listContent5.imgRes = R.drawable.default_img;
        listContent5.mwKey = Config.MW_1;
        listContent5.imgUrl = imgUrls[1];
        list.add(listContent5);
        return this;
    }

}

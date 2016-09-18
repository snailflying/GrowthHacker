package cn.mw.growthhacker.domain;

import java.util.ArrayList;
import java.util.List;

import cn.mw.growthhacker.Config;
import cn.mw.growthhacker.R;

/**
 * @author aaron
 * @date 16/3/3
 */
public class PushGridData {
    public List<ListContent> list;

    public static class ListContent {
        public int imgRes;
        public String mwKey;
        public String imgPath;
    }

    public PushGridData builder(){
        list = new ArrayList<>();
        ListContent listContent = new ListContent();
        listContent.imgRes = R.drawable.icon_img1;
        listContent.mwKey = Config.MW_1;
        list.add(listContent);

        ListContent listContent1 = new ListContent();
        listContent1.imgRes = R.drawable.icon_img2;
        listContent1.mwKey = Config.MW_2;
        list.add(listContent1);

        ListContent listContent3 = new ListContent();
        listContent3.imgRes = R.drawable.add;
        list.add(listContent1);
//        ListContent listContent2 = new ListContent();
//        listContent2.desc = "来自[迷橙]";
//        listContent2.mwKey = Config.MW_1;
//        list.add(listContent2);

        return this;
    }

}

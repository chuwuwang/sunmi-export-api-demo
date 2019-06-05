package com.sm.l3.demo.print;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator on 2016/6/20.
 */
public class BankInfo {

    private static final Map<String, String> bankInfoMap = new HashMap<>();

    public static String getName(String code) {
        if (code == null || code.length() == 0) {
            return code;
        }
        if (bankInfoMap.size() == 0) {
            initMap();
        }
        if ( bankInfoMap.containsKey(code) ) {
            return bankInfoMap.get(code);
        } else {
            return code;
        }
    }

    public static String getCode(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        if (bankInfoMap.size() == 0) {
            initMap();
        }
        if ( bankInfoMap.containsValue(name) ) {
            for ( Map.Entry<String, String> entry : bankInfoMap.entrySet() ) {
                String key = entry.getKey();
                String value = entry.getValue();
                if ( TextUtils.equals(value, name) ) {
                    return key;
                }
            }
        }
        return name;
    }

    private static void initMap() {
        bankInfoMap.put("03060000", "广发银行");
        bankInfoMap.put("03070000", "深发银行");
        bankInfoMap.put("03100000", "浦发银行");
        bankInfoMap.put("04030000", "平安银行");
        bankInfoMap.put("03110000", "北京银行");
        bankInfoMap.put("04010000", "上海银行");
        bankInfoMap.put("48022220", "钜阵网络");
        bankInfoMap.put("48212200", "通联支付");
        bankInfoMap.put("03114650", "恒丰银行");
        bankInfoMap.put("03163310", "浙商银行");
        bankInfoMap.put("63030000", "光大银行");
        bankInfoMap.put("48691000", "银联数据");

        bankInfoMap.put("01000000", "邮储银行");
        bankInfoMap.put("01020000", "工商银行");
        bankInfoMap.put("01030000", "农业银行");
        bankInfoMap.put("01033320", "宁波市农业银行");
        bankInfoMap.put("01040000", "中国银行");
        bankInfoMap.put("01050000", "建设银行");
        bankInfoMap.put("01059999", "中国建设银行");
        bankInfoMap.put("03010000", "交通银行");
        bankInfoMap.put("03020000", "中信银行");
        bankInfoMap.put("03030000", "光大银行");
        bankInfoMap.put("03040000", "华夏银行");
        bankInfoMap.put("03050000", "民生银行");
        bankInfoMap.put("03060000", "广东发展银行");
        bankInfoMap.put("03070000", "深圳发展银行");
        bankInfoMap.put("03080000", "招商银行");
        bankInfoMap.put("03090000", "兴业银行");
        bankInfoMap.put("03100000", "浦东发展银行");
        bankInfoMap.put("03114560", "恒丰银行");
        bankInfoMap.put("03131100", "天津市商业银行");
        bankInfoMap.put("03133930", "厦门市商业银行");
        bankInfoMap.put("03134500", "济南商业银行");
        bankInfoMap.put("03134530", "淄博商业银行");
        bankInfoMap.put("03134560", "烟台商业银行");
        bankInfoMap.put("03134580", "潍坊商业银行");
        bankInfoMap.put("03134730", "临沂商业银行");
        bankInfoMap.put("03134732", "日照市商业银行");
        bankInfoMap.put("03160000", "浙商银行");
        bankInfoMap.put("03170000", "渤海银行");
        bankInfoMap.put("03190001", "花旗银行(中国) 有限公司");
        bankInfoMap.put("03200000", "东亚银行中国有限公司");
        bankInfoMap.put("03210000", "汇丰银行(中国) 有限公司");
        bankInfoMap.put("03220000", "渣打银行中国有限公司");
        bankInfoMap.put("03240000", "星展银行");
        bankInfoMap.put("03260000", "恒生银行");
        bankInfoMap.put("03270000", "友利银行(中国) 有限公司");
        bankInfoMap.put("04012900", "上海银行");
        bankInfoMap.put("04031000", "北京银行");
        bankInfoMap.put("04053910", "福州商业银行");
        bankInfoMap.put("04062410", "长春市商业银行");
        bankInfoMap.put("04073140", "镇江市商业银行");
        bankInfoMap.put("04083320", "宁波银行");
        bankInfoMap.put("04105840", "平安银行");
        bankInfoMap.put("04115010", "焦作市商业银行");
        bankInfoMap.put("04123330", "温州银行");
        bankInfoMap.put("04135810", "广州市商业银行");
        bankInfoMap.put("04145210", "汉口银行");
        bankInfoMap.put("04162640", "齐齐哈尔商业银行");
        bankInfoMap.put("04172210", "盛京银行");
        bankInfoMap.put("04184930", "洛阳银行");
        bankInfoMap.put("04192310", "辽阳市商业银行");
        bankInfoMap.put("04202220", "大连银行");
        bankInfoMap.put("04213050", "苏州市商业银行");
        bankInfoMap.put("04221210", "石家庄商业银行");
        bankInfoMap.put("04233310", "杭州商业银行");
        bankInfoMap.put("04240001", "南京银行");
        bankInfoMap.put("04256020", "东莞市商业银行");
        bankInfoMap.put("04263380", "金华商业银行");
        bankInfoMap.put("04270001", "乌鲁木齐市商业银行");
        bankInfoMap.put("04283370", "绍兴商业银行");
        bankInfoMap.put("04296510", "成都商业银行");
        bankInfoMap.put("04302240", "抚顺市商业银行");
        bankInfoMap.put("04325210", "宜昌市商业银行");
        bankInfoMap.put("04332350", "葫芦岛市商业银行");
        bankInfoMap.put("04341100", "天津市商业银行");
        bankInfoMap.put("04341101", "天津银行");
        bankInfoMap.put("04354910", "郑州商业银行");
        bankInfoMap.put("04360010", "宁夏银行");
        bankInfoMap.put("04375850", "珠海市商业银行");
        bankInfoMap.put("04392270", "锦州市商业银行");
        bankInfoMap.put("04403600", "徽商银行");
        bankInfoMap.put("04416530", "重庆市商业银行");
        bankInfoMap.put("04422610", "哈尔滨银行");
        bankInfoMap.put("04437010", "贵阳市商业银行");
        bankInfoMap.put("04447910", "西安商业银行");
        bankInfoMap.put("04453020", "无锡市商业银行");
        bankInfoMap.put("04462260", "丹东市商业银行");
        bankInfoMap.put("04478210", "兰州市商业银行");
        bankInfoMap.put("04484210", "南昌商业银行");
        bankInfoMap.put("04491610", "太原市商业银行");
        bankInfoMap.put("04504520", "青岛商行");
        bankInfoMap.put("04512420", "吉林市商业银行");
        bankInfoMap.put("04523060", "南通商业银行");
        bankInfoMap.put("04544240", "九江市商业银行");
        bankInfoMap.put("04562230", "鞍山市商业银行");
        bankInfoMap.put("04588510", "青海银行");
        bankInfoMap.put("04593450", "台州市商业银行");
        bankInfoMap.put("04603110", "盐城商行");
        bankInfoMap.put("04615510", "长沙市商业银行");
        bankInfoMap.put("04634280", "赣州银行股份有限公司");
        bankInfoMap.put("04643970", "泉州市商业银行");
        bankInfoMap.put("04652280", "营口市商业银行");
        bankInfoMap.put("04667310", "昆明商业银行");
        bankInfoMap.put("04672290", "阜新市商业银行");
        bankInfoMap.put("04703350", "嘉兴市商业银行");
        bankInfoMap.put("04721460", "廊坊银行");
        bankInfoMap.put("04733450", "泰隆城市信用社");
        bankInfoMap.put("04741910", "呼市商业银行");
        bankInfoMap.put("04753360", "湖州市商业银行");
        bankInfoMap.put("04786110", "南宁市商业银行");
        bankInfoMap.put("04791920", "包头市商业银行");
        bankInfoMap.put("04803070", "连云港市商业银行");
        bankInfoMap.put("04814650", "威海商业银行");
        bankInfoMap.put("04836560", "攀枝花市商业银行");
        bankInfoMap.put("04856590", "绵阳市商业银行");
        bankInfoMap.put("04866570", "泸州市商业银行");
        bankInfoMap.put("04871620", "大同市商业银行");
        bankInfoMap.put("04885050", "三门峡市商业银行");
        bankInfoMap.put("04895910", "湛江市商业银行");
        bankInfoMap.put("04901380", "张家口市商业银行股份有限公司");
        bankInfoMap.put("04916170", "桂林市商业银行");
        bankInfoMap.put("04922690", "大庆市商业银行");
        bankInfoMap.put("04933120", "靖江市长江城市信用社");
        bankInfoMap.put("04943030", "徐州市商业银行");
        bankInfoMap.put("04956140", "柳州市商业银行");
        bankInfoMap.put("04966730", "南充市商业银行");
        bankInfoMap.put("04974634", "莱芜银行");
        bankInfoMap.put("04986580", "德阳市商业银行");
        bankInfoMap.put("04991240", "唐山市商业银行");
        bankInfoMap.put("05007020", "六盘水商行");
        bankInfoMap.put("05027360", "曲靖市商业银行");
        bankInfoMap.put("05031680", "晋城商业银行");
        bankInfoMap.put("05056020", "东莞商行");
        bankInfoMap.put("05063330", "温州银行");
        bankInfoMap.put("05075210", "汉口银行");
        bankInfoMap.put("05083000", "江苏银行");
        bankInfoMap.put("05105840", "平安银行股份有限公司");
        bankInfoMap.put("05121660", "长治市商业银行");
        bankInfoMap.put("05131410", "承德市商业银行");
        bankInfoMap.put("05154680", "德州市商业银行");
        bankInfoMap.put("05167030", "遵义市商业银行");
        bankInfoMap.put("05171270", "邯郸市商业银行");
    }

}

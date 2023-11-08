package com.github.zhangguoqing.service.oauth;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StaticH5Log {
    private static Map<String, Set<String>> urlMap = new HashMap<>();
    /**
     * 输出的日志文件
     */
    private static String printLog = "H5_PRINT_LOG.log";
    private static int totalCount;
    private static int failCount;
    private static int filterCount = 0;

    public static void main(String[] args) {
        // 根据实际情况修改文件路径
//        String filePath = "/Users/zhangguoqing/work/H5Log/NATIVE_INTERFACE_DTL.2023-09-05-11.ningbo02.196_112_5_172.log";
        String filePath = "/Users/zhangguoqing/work/H5Log/H5_COLLECT_LOG.log";
        BufferedReader reader = null;
        try {
             reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) { // 按行读取文件直到文件末尾
                totalCount++;
                String[] splitArr = line.split("\\|");
                try {
                    if (splitArr != null && splitArr.length > 0) {
                        String time = splitArr[0];
                        String provinceCode = splitArr[1].substring(2);
                        String cityCode = splitArr[2].substring(2);
                        String version = splitArr[4].substring(2);
                        //只取域名，不要参数
                        String[] urlArr = splitArr[7].substring(2).split("\\?");
                        String url = urlArr[0];
                        //过滤掉灰度环境域名
                        boolean ifFilter = filterSpecialUrl(url);
                        if (ifFilter) {
                            continue;
                        }
                        String method;
                        if (!StringUtils.isBlank(splitArr[21])) {
                            method = splitArr[21].substring(2);
                        } else if (!StringUtils.isBlank(splitArr[23])){
                            method = splitArr[23].substring(2);
                        } else {
                            method = splitArr[27].substring(2);
                        }
                        Set<String> methods = urlMap.get(url);
                        if (CollectionUtils.isEmpty(methods)) {
                            Set<String> tempMethods = new HashSet<>();
                            tempMethods.add(method);
                            urlMap.put(url, tempMethods);
                        } else {
                            methods.add(method);
                        }
                    }
                } catch(Throwable throwable){
                    failCount++;
                    System.out.println(throwable.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(filterCount);
        System.out.println("urlCount:" + urlMap.size() + ", totalCount:" + totalCount + ",failCount:" + failCount);
        for (Map.Entry<String, Set<String>> entry : urlMap.entrySet()) {
            String url = entry.getKey();
            Set<String> methods = entry.getValue();
            String methodStr = methods.stream().collect(Collectors.joining(","));
            System.out.println(url);
//            for (String method : methods) {
//                if ("#".equals(method)) {
//                    continue;
//                }
//                System.out.println(url + "  " + method);
//            }
        }
    }

    private static boolean filterSpecialUrl(String url) {
        String regex = null;
        //过滤掉测试地址
        if (url.contains("dev")) {
            return true;
        }
        //把非h5的网站的去掉：www.10086.cn
        regex = ".www\\.10086\\.cn";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //把非h5的网站的去掉：music.migu.cn
        regex = ".music\\.migu\\.cn";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉商品相关的地址
        regex = ".goods\\/[0-9_]*+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdmark相关地址
        regex = ".qwhdmark\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hnmccClientWap/2021/08/discountsFiveG/index.html相关的地址
        regex = ".hnmccClientWap\\/[0-9]+\\/[0-9]+\\/discountsFiveG";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hnmccClientWap/2021/07/specialPackage/index.html相关的地址
        regex = ".hnmccClientWap\\/[0-9]+\\/[0-9]+\\/specialPackage\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hnmccClientWap/2021/07/specialPackage_2/index.html相关的地址
        regex = ".hnmccClientWap\\/[0-9]+\\/[0-9]+\\/specialPackage_2\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hnmccClientWap/2021/07/signNew/index.html相关的地址
        regex = ".hnmccClientWap\\/[0-9]+\\/[0-9]+\\/signNew\\/index\\.";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hnmccClientWap/2021/07/signNew/indexGroup相关的地址
        regex = ".hnmccClientWap\\/[0-9]+\\/[0-9]+\\/signNew\\/indexGroup";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hnmccClientWap/2020/06/mySetMeal/common相关的地址
        regex = ".hnmccClientWap\\/[0-9]+\\/[0-9]+\\/mySetMeal\\/common";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hnmccClientWap/2020/06/mySetMeal/dailyrent相关的地址
        regex = ".hnmccClientWap\\/[0-9]+\\/[0-9]+\\/mySetMeal\\/dailyrent";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hnmccClientWap/2019/04/voicePacket/index相关的地址
        regex = ".hnmccClientWap\\/[0-9]+\\/[0-9]+\\/voicePacket\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hnmccClientWap/2019/03/points/index.html
        regex = ".hnmccClientWap\\/[0-9]+\\/[0-9]+\\/points\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/pointclock/1023021501相关的地址
        regex = ".qwhdhub\\/pointclock\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/guaguaka/1023071808相关的地址
        regex = ".qwhdhub\\/guaguaka\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/giveprize/1023082951#/相关的地址
        regex = ".qwhdhub\\/giveprize\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/turntable/1023081502相关的地址
        regex = ".qwhdhub\\/turntable\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/tearbox/1023071820相关的地址
        regex = ".qwhdhub\\/tearbox\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/goldeneggs/1023082408相关的地址
        regex = ".qwhdhub\\/goldeneggs\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/gashapon/1023083037相关的地址
        regex = ".qwhdhub\\/gashapon\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/flipcard/1023081806相关的地址
        regex = ".qwhdhub\\/flipcard\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/quiz/1023083029#/相关的地址
        regex = ".qwhdhub\\/quiz\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/ninesquares/1023070308相关的地址
        regex = ".qwhdhub\\/ninesquares\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/slc/visit/1023083013/bmu2eu相关的地址
        regex = ".qwhdhub\\/slc\\/visit\\/[0-9]+\\/[a-zA-Z]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/survey/1023082312相关的地址
        regex = ".qwhdhub\\/survey\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/wordprize/1023082817相关的地址
        regex = ".qwhdhub\\/wordprize\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/prelogin/bindprize/1023082316相关的地址
        regex = ".qwhdhub\\/prelogin\\/bindprize\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉qwhdhub/slotmachine/1023062702相关的地址
        regex = ".qwhdhub\\/slotmachine\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉h/d/ah/2022/whkd/kdwjcj/index.html相关的地址
        regex = ".h\\/d\\/ah\\/[0-9]+\\/whkd\\/kdwjcj\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉h/d/ah/AC20230103228047/index_app.html相关的地址
        regex = ".h\\/d\\/ah\\/[0-9A-Za-z]+\\/index_app";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉h/d/hd/AC20220726567428/index.html相关的地址
        regex = ".h\\/d\\/hd\\/[0-9_A-Za-z]+\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉h/d/hd/AC20230103796084/index_web.html相关的地址
        regex = ".h\\/d\\/hd\\/[0-9_A-Za-z]+\\/index_web";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉skin相关地址
        regex = ".hd\\/skin\\/[0-9]+\\/[0-9_]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hd/qgqqw/731.html相关的地址
        regex = ".hd\\/qgqqw\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hd/bhk/471.html相关的地址
        regex = ".hd\\/bhk\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉mmplatClientWap/actWap/202101120004_zhengshi/202101120004/download相关的地址
        regex = ".mmplatClientWap\\/actWap\\/[0-9_A-Za-z]+\\/[0-9]+\\/download";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉mmplatClientWap/actWap/202006230002_zhengshi/202006230002/index相关的地址
        regex = ".mmplatClientWap\\/actWap\\/[0-9_A-Za-z]+\\/[0-9]+\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉cmcc_activity/2023/act25/index.html相关的地址
        regex = ".cmcc_activity\\/[0-9]+\\/act[0-9]+\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉emall/static/h5/model/qwe/1861801309600002.html相关的地址
        regex = ".emall\\/static\\/h5\\/model\\/qwe\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉client/2023/smallCell/index.html相关的地址
        regex = ".client\\/[0-9]+\\/smallCell\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉client/2021/coinPark/index.html相关的地址
        regex = ".client\\/[0-9]+\\/coinPark\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉cmcc_vact/task-tree/index.html#/stealing/compare/143556/962ef41379484bff8d00f13311ccbf28相关的地址
        regex = ".cmcc_vact\\/task-tree\\/index\\.html#\\/stealing\\/compare\\/[0-9]+\\/[0-9_A-Za-z]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉zt-portal/gxhzg/portal/hzg_gx/template/20220112105245669相关的地址
        regex = ".zt-portal\\/gxhzg\\/portal\\/hzg_gx\\/template\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉zt-portal/gxhzg/portal/hzg_gx/pointZone/index/201308211019相关的地址
        regex = ".zt-portal\\/gxhzg\\/portal\\/hzg_gx\\/pointZone\\/index\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hyh_release/h5/static/biz/52bfda7d3a5c2a927c28dd260a0cf99a相关的地址
        regex = ".hyh_release\\/h5\\/static\\/biz\\/[0-9_A-Za-z]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉customerService/auth/V6Bm3jkNKMI8KPPtca4aI9Nq6xBABmsk相关的地址
        regex = ".customerService\\/auth\\/[0-9_A-Za-z]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉h5web/template/T1CNVTB_bT1RXx1p6K.html相关的地址
        regex = ".h5web\\/template\\/[0-9_A-Za-z]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉vw/navbar/WSCZYLOne_soloYY相关的地址
        regex = ".vw\\/navbar\\/[0-9_A-Za-z]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉vw/TCTJ相关的地址
        regex = ".vw\\/[^navbar]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉app/act/resource/2109/html/index.html相关的地址
        regex = ".app\\/act\\/resource\\/[0-9]+\\/html\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉whalecallback/delivery/pagevisit/releaseVisit/61713相关的地址
        regex = ".pagevisit\\/releaseVisit\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉portal/static/wx/5cee3c07-a6be-4951-9e1a-036a95c298b8.html相关的地址
        regex = ".portal\\/static\\/wx\\/[0-9a-zA-Z-]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉template/20220615/duoxuanyi/index.html相关的地址
        regex = ".template\\/[0-9]+\\/duoxuanyi\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉szhy/2020/20201221/index.html相关的地址
        regex = ".szhy\\/[0-9]+\\/[0-9]+\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉mall_771_771.html相关的地址
        regex = ".mall_[0-9_]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉middleh5/gamedetail/400583873相关的地址
        regex = ".middleh5\\/gamedetail\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉miguplay/middleGame/gameplay/400859889相关的地址
        regex = ".miguplay\\/middleGame\\/gameplay\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉mo-h5-new/r/25404827相关的地址
        regex = ".mo-h5-new\\/r\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉app/act/resource/2109/html/index.html相关的地址
//        regex = ".app\\/act\\/resource\\/[0-9]+\\/html\\/index";
//        pattern = Pattern.compile(regex);
//        matcher = pattern.matcher(url);
//        if (matcher.find()) {
//            System.out.println(url);
//            return true;
//        }
        //过滤掉complaint/ssocomplaints/2A6620117A53EDEEE96CF6F60085BE6D相关的地址
        regex = ".complaint\\/ssocomplaints\\/[0-9A-Za-z]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉list/140_771_771_1_0_0_0.html相关的地址
        regex = ".list\\/[0-9_]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉openh5/mobilestaticize/5GCXBDXLLB2023B_NVS.html相关的地址
        regex = ".openh5\\/mobilestaticize\\/[0-9A-Za-z]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉openh5/mobilestaticize/5GCXBDXLLB2023B_NVS.html相关的地址
        regex = ".penh5\\/mobilestaticize\\/[0-9A-Za-z_]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉resources/zjec/images/zjec_16328201845664440.html
        regex = ".resources\\/zjec\\/images\\/zjec_[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉nact/resource/2335/html/index.html
        regex = ".nact\\/resource\\/[0-9]+\\/html\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉nact/resource/2335/html/prize.html
        regex = ".nact\\/resource\\/[0-9]+\\/html\\/prize";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hlwyxhdhub/act-appnewuser/1023022013
        regex = ".hlwyxhdhub\\/act-appnewuser\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉hlwyxhdhub/act-blindbox/1023062508
        regex = ".hlwyxhdhub\\/act-blindbox\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        //过滤掉cmbh3/h5/authToken.html;jsessionid=
        regex = ".cmbh3\\/h5\\/authToken\\.html;jsessionid=";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }


        //新添加
        //过滤掉mpad/pad/act2018/act/AC20221104437606/index_app.html
        regex = ".mpad\\/pad\\/act2018\\/act\\/[A-Z0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }

        //过滤掉mb_nact/new/microform//formview/1130215053041025
        regex = ".mb_nact\\/new\\/microform\\/\\/formview\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }

        //过滤掉https://wap.jx.10086.cn/hui/release/polymerization/100141/index.html
        regex = ".hui\\/release\\/polymerization\\/[0-9]+\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }

        //过滤掉https://m.sd.10086.cn/sdSLServer/WYebOo7
        regex = ".sdSLServer\\/[0-9a-zA-Z]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }

        //过滤掉https://touch.10086.cn/hd/skin/821198/index.html
        regex = ".hd\\/skin\\/[0-9]+\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }

        //过滤掉http://gx.10086.cn/zt-portal/gxhzg/portal/hzg_wap/template/20221020165941761
        regex = ".portal\\/hzg_wap\\/template\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }

        //过滤掉https://wap.jx.10086.cn/hui/release/polymerization/100182/index.html
        regex = ".release\\/polymerization\\/[0-9]+\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }

        //过滤掉https://www.migufun.com/middleh5/labelList/2157
        regex = ".middleh5\\/labelList\\/[0-9]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }

        //过滤掉https://m.sd.10086.cn/sd_fe_service/sd2440/index.html#/
        regex = ".sd_fe_service\\/sd[0-9]+\\/index";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }

        //过滤掉https://touch.10086.cn/discount/551_551.html
        regex = ".discount\\/[0-9_]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(url);
        if (matcher.find()) {
//            System.out.println(url);
            return true;
        }
        return false;
    }
}

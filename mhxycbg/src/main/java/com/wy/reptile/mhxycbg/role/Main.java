package com.wy.reptile.mhxycbg.role;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wy.reptile.common.http.apache.HttpGet;

public class Main {

	private static final String Cookie = "vjuids=828f39c7.15c80937a58.0.04c9bc1ed7b97; mail_psc_fingerprint=60bf59622d78c8357ee8d2d00599ef9c; _ngd_tid=kMyGJGE4ix70WScBn%2B1K7JslCId%2BDDat; _ga=GA1.2.448130814.1498183668; __utma=187553192.448130814.1498183668.1520385721.1522813676.4; __utmz=187553192.1520385721.3.3.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __oc_uuid=efefc9f0-21a5-11e8-8e47-81531415a913; __f_=1526433682476; _ntes_nnid=29e9c6598b7213b6c267e73cdffd680e,1527057223292; _ntes_nuid=29e9c6598b7213b6c267e73cdffd680e; usertrack=O2+gyltIFKdsrYcbAwPjAg==; vjlast=1496805768.1532402126.21; NTES_PASSPORT=7qeh7F5T6wqfe5altDG7kUTmoBw1xuPoopXEo3H2Pj8MsHBnse9N0v2RuYRQBhkRHlXvihWFB_FDNW.BPH58YV6oppe4QYgORDv7KfGvM9ydPfGR.PNiFK8fZZAmSIeqdiZGW_3LqqPjnqvjIXK.QjrFoB6NcOQBiD80JrhK_ufK65ymepmUjyWFN; P_INFO=m15210068901@163.com|1532418254|1|mail163|00&99|bej&1532403291&mail163#bej&null#10#0#0|152901&1|kaola&mail163&game|15210068901@163.com; nts_mail_user=15210068901@163.com:-1:1; NTES_CMT_USER_INFO=96557707%7Cm152****8901%7C%7Cfalse%7CbTE1MjEwMDY4OTAxQDE2My5jb20%3D; vinfo_n_f_l_n3=f1975aacc4b52f14.1.94.1495848138244.1532402134042.1533029754838; __session__=1; fingerprint=2511175395; area_id=30; cur_servername=%25E9%259B%2581%25E9%2597%25A8%25E5%2585%25B3; sid=f07SWekpPA9hlZ3lNQvLZEVTqiZDJyg3QuoTp3ic; last_login_serverid=421; wallet_data=%7B%22is_locked%22%3A%20false%2C%20%22checking_balance%22%3A%200%2C%20%22balance%22%3A%200%2C%20%22free_balance%22%3A%200%7D; latest_views=421_3421126-421_3543035-421_3536943-421_3569174-421_3536144-407_3935392-407_3935390-407_3935389-407_3935388-407_3935387-421_3551469";

	public static void main(String[] args) {
//		list(1);
		role("201807181500113-421-EKHAUKVCOHK2");
	}

	private static void list(int page) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Cookie", Cookie);

		String url = "https://xyq.cbg.163.com/cgi-bin/query.py?act=search_role&server_id=421&areaid=30&server_name=%D1%E3%C3%C5%B9%D8&level_min=109&level_max=109&school=15";
		url += ("&page=" + page);
		String body = HttpGet.getAsString(url, headers, "gb2312");

		Pattern pattern = Pattern.compile("<a href=\"(.*?)\"  target=\"_blank\" class=\"soldImg\"");

		Matcher m = pattern.matcher(body);
		while (m.find()) {
			String s = m.group(1);
			int begin = s.indexOf("eid=") + 4;
			System.out.println(s.substring(begin, begin + 32));
			Insert.roleList(s.substring(begin, begin + 32), 1);
		}

		Pattern paegNum = Pattern.compile("共(.*?)页 第");
		Matcher pageM = paegNum.matcher(body);
		if (pageM.find()) {
			int pageEnd = Integer.parseInt(pageM.group(1));
			if (page < pageEnd) {
				list(page + 1);
			}

		}
	}

	private static void role(String eid) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Cookie", Cookie);
		String url = "https://xyq.cbg.163.com/equip?s=421&eid="+eid+"&equip_refer=33&view_loc=search_cond";
		String body = HttpGet.getAsString(url, headers, "gb2312");
		System.out.println(body);
	}
}

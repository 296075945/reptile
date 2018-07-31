package com.wy.reptile.mhxycbg.role;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wy.reptile.common.http.apache.HttpGet;

public class Main {

	private static final String Cookie = "return_url=; vjuids=-347724c48.15accfbbe1c.0.921bc42e83f8c; mail_psc_fingerprint=ca9c7bc88ddcc6b7fbdcca0e207eaad9; _ntes_nnid=f1211b41dff4b19eb51f4684caa70ea5,1514732359760; _ntes_nuid=f1211b41dff4b19eb51f4684caa70ea5; __utma=187553192.1618016211.1517027628.1517027628.1517031891.2; mp_MA-9506-0E9D982F1392_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fbjk.163.com%2Fhongka%2F%3Fcmpid%3Djt-wyk-mhxy%26shopid%3Dlmk.cps.jtwykmhxy%22%2C%22updatedTime%22%3A%201519312477318%2C%22sessionStartTime%22%3A%201519312477297%2C%22deviceUdid%22%3A%20%2251b43d09-6bf6-4170-94fb-8bd1e66b0820%22%2C%22initial_referrer%22%3A%20%22%24direct%22%2C%22initial_referring_domain%22%3A%20%22%24direct%22%2C%22persistedTime%22%3A%201519312477288%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_u_logout%22%2C%22time%22%3A%201519312477319%7D%2C%22sessionUuid%22%3A%20%220ec5224d-243d-4c46-ab97-0fc3fa558c0f%22%2C%22superProperties%22%3A%20%7B%22cmpid%22%3A%20%22jt-wyk-mhxy%22%7D%7D; _ngd_tid=8N8p0VGa7d8GEtamolNEULqYzG%2BSJX3c; vjlast=1489497735.1527399207.12; vinfo_n_f_l_n3=bee81a6422b4f4b1.1.26.1479904184244.1527418729708.1527422145030; usertrack=ezq0pVszKRKbRP3aBDebAg==; _ga=GA1.2.1618016211.1517027628; area_id=30; nts_mail_user=15210068901@163.com:-1:1; last_login_roleid=17527687; last_login_role_serverid=421; recommend_typeids=1,2,4,3,1; recommend_url=https://xyq.cbg.163.com/cgi-bin/query.py?act=recommend_search&recommend_type=1; fingerprint=2881782602; NTES_PASSPORT=Nd4O3MfvZcPLuqZ5otK8lzD7U5sHjlEhm037L_6B5QmX.6Js.4TFkUBpiyphJZzp6Y3UqZAVJEVrGuBw7zrozMLAVSUftlEV0tXnMGiD5za.5ROpt5FqVjmRIICuwD4oPqIOAE_Koo5QsoUQD3jthQSVLJMFvnhJqcmkeSZjEiRjMa9u40urQ9AVF; P_INFO=m15210068901@163.com|1532686768|1|mail163|00&99|bej&1532618239&cc#bej&null#10#0#0|152901&1|kaola&mail163&cc|15210068901@163.com; __session__=1; cur_servername=%25E9%259B%2581%25E9%2597%25A8%25E5%2585%25B3; latest_views=421_3435978-421_3551469-421_3472280-421_3421126-421_3536943-421_3426366-421_3553312-421_3540538-421_3557519-421_3536144-421_3492920-421_3557503-421_3540239-421_3480823-421_3563694-421_3568861-421_3530569-421_3566742-421_3115538-421_3545207; sid=oNMraJKbh3HblmBaRT_e3kprA8zgX_Xmuf0fiBPW; last_login_serverid=421; wallet_data=%7B%22is_locked%22%3A%20false%2C%20%22checking_balance%22%3A%200%2C%20%22balance%22%3A%200%2C%20%22free_balance%22%3A%200%7D";

	public static void main(String[] args) {
		list(1);
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

	}
}

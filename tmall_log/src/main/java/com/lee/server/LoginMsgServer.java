package com.lee.server;

import javax.jws.WebService;
/**
* @ClassName: LoginMsgServer
* @Description: TODO(不使用webservice, 下面的service也不用)
* @author A18ccms a18ccms_gmail_com
* @date 2018年4月12日 下午4:56:37
*
 */
@WebService
public interface LoginMsgServer {
	public void sendMessage(String json_user);
}

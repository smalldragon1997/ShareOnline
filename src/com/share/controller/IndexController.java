package com.share.controller;

import com.share.entity.Type;
import com.share.json.MsgJson;
import com.share.service.IndexService;
import com.share.tools.MyMsgJson;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/main" })
public class IndexController {

	@Autowired
	@Qualifier(value = "indexService")
	private IndexService indexService;

	// 首页热门共享 列表获取请求
	@RequestMapping(value = "/indexHotList")
	@ResponseBody
	public MsgJson<String, Object> IndexHotList() {
		// 获取service传来的封装数据
		MsgJson<String, Object> msgFromService = indexService.getIndexHotList();
		return msgFromService;
	}

	// 首页轮播图 获取请求
	@RequestMapping(value = "/indexSlideList")
	@ResponseBody
	public MsgJson<String, Object> IndexSlideList() {
		// 获取service传来的封装数据
		MsgJson<String, Object> msgFromService = indexService.getIndexSlideList();

		return msgFromService;
	}

	// 首页商品列表
	@RequestMapping(value = { "/indexCommList" })
	@ResponseBody
	public MsgJson<String, Object> IndexCommList(HttpServletRequest request) {
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		// 请求路径
		msgToService.setRequestData("imgPath", request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath());
		MsgJson<String, Object> msgFromService = indexService.getIndexCommList(msgToService);
		return msgFromService;
	}

	// 首页跳转其他页面
	@RequestMapping(value = "/turn/{page}")
	public ModelAndView turnToPage(@PathVariable String page) {
		ModelAndView mv = new ModelAndView(page);
		return mv;
	}

	// 修改商品
	@RequestMapping(value = "reviseComm")
	@ResponseBody
	public ModelAndView reviseComm(int commId, HttpSession session) {

		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		msgToService.setRequestData("commId", commId);
		msgToService.setSessionData("user", session.getAttribute("user"));
		msgToService.setSessionData("isLogin", session.getAttribute("isLogin"));
		// 初始化 返回View的数据
		MsgJson<String, Object> msgFromView = MyMsgJson.newMsgjson();
		// 操作
		msgFromView = indexService.reviseComm(msgToService);
		
		ModelAndView mv = new ModelAndView();
		MyMsgJson.setData(msgFromView, session);
		mv.setViewName((String) msgFromView.getJsonData("page"));
		return mv;
	}

	// 获取登入状态
	@RequestMapping(value = "/getLoginState")
	@ResponseBody
	public MsgJson<String, Object> getLoginState(@RequestBody String view, HttpSession session) {
		// 初始化 返回View的数据
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();
		// 判断是否登录
		if ("yes".equals(session.getAttribute("isLogin"))) {
			msgToView.setJsonData("page", "/shareOnline/main/turn/" + view);
		} else {
			msgToView.setMsg("你还没有登录哦，无法进行操作");
			msgToView.setState(false);
		}
		return msgToView;
	}
	//进入查询页面
	@RequestMapping(value = "/getCommPageByType")
	@ResponseBody
	public ModelAndView getLoginState(int typeId,String query,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		session.setAttribute("typeId", typeId);
		session.setAttribute("query", query);
		mv.setViewName("query");
		return mv;
	}
}

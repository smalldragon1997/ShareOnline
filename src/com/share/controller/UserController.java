package com.share.controller;

import com.share.json.*;
import com.share.service.UserService;
import com.share.tools.MyMsgJson;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/user" })
public class UserController {
	@Autowired
	@Qualifier(value = "userService")
	UserService userService;

	// 用户注册
	@RequestMapping(value = "/register")
	@ResponseBody
	public MsgJson<String, Object> register(@RequestBody RegisterJson registerFromView, HttpSession session) {
		// 封装前台数据到MsgJson中
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		msgToService.setJsonData("registerJson", registerFromView);

		// 传入业务层已获取并封装的json表单参数 并接收业务层处理注册服务的结果
		MsgJson<String, Object> msgToView = userService.register(msgToService);

		// 如果注册成功 设置session属性 传到前端页面
		if (msgToView.getState()) {
			// 设置跳转页面
			msgToView.setJsonData("page", "/shareOnline/user/index");
			// session或response响应
			MyMsgJson.setData(msgToView, session);
			return msgToView;
		} else {
			// 注册失败 直接返回错误信息
			return msgToView;
		}

	}

	// 用户访问首页，进行登录验证
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpSession session, HttpServletRequest request) {

		// 设置初始响应viewname
		ModelAndView mv = new ModelAndView("index");

		// 封装session和request到msgToService里
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		msgToService.setSessionData("isLogin", session.getAttribute("isLogin"));
		msgToService.setRequestData("cookies", request.getCookies());
		//
		// if(MyCookie.getCookie(request.getCookies(), "shareOnlineCookie") ==
		// null){
		// System.out.println("在controller获取不到cookie");
		// }else{
		// System.out.println("在controller获取到cookie");
		// }

		// 执行自动登录操作 得到业务的处理的返回结果（操作信息 状态和 属性值）
		MsgJson<String, Object> msgFromService = userService.freeLogin(msgToService);

		// 如果自动登录成功 设置返回前端的属性 否则直接返回mv
		if (msgFromService.getState()) {
			// 设置用户名 保存登录状态
			MyMsgJson.setData(msgFromService, session);

			return mv;
		} else {
			return mv;
		}
	}

	// 用户登录/保存cookie
	@RequestMapping(value = "/login")
	@ResponseBody
	public MsgJson<String, Object> login(@RequestBody LoginJson loginJsonFromView, HttpServletResponse response,
			HttpSession session) {

		// 封装前台数据到MsgJson中
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		msgToService.setJsonData("loginJson", loginJsonFromView);

		// 传入业务层已获取并封装的json表单参数 并接收业务层处理登录服务的结果
		MsgJson<String, Object> msgToView = userService.login(msgToService);

		// 如果登录成功 设置session response属性 传到前端页面
		if (msgToView.getState()) {
			// 设置跳转页面
			msgToView.setJsonData("page", "/shareOnline/user/index");
			// session或response响应
			MyMsgJson.setData(msgToView, session);
			MyMsgJson.setData(msgToView, response);
			return msgToView;
		} else {
			// 登录失败 直接返回错误信息
			return msgToView;
		}
	}

	// 用户退出登录/删除cookie
	@RequestMapping(value = "/exit")
	@ResponseBody
	public MsgJson<String, Object> exit(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 封装数据到Msgjson中传给业务层
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		msgToService.setRequestData("cookies", request.getCookies());

		// 封装从业务层返回 并需要传回view的数据
		MsgJson<String, Object> msgToView = userService.exit(msgToService);

		// 设置跳转页面
		msgToView.setJsonData("page", "/shareOnline/user/index");

		// 处理需要返回给view的数据
		MyMsgJson.setData(msgToView, response);
		MyMsgJson.setData(msgToView, session);

		return msgToView;
	}

	@RequestMapping(value = "/test")
	@ResponseBody
	public LoginJson Test(@RequestBody LoginJson loginJsonFromView, HttpSession session) {
		return loginJsonFromView;
	}

}

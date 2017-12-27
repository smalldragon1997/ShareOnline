package com.share.controller;

import com.share.entity.Type;
import com.share.json.*;
import com.share.service.CommService;
import com.share.tools.MyMsgJson;
import java.util.List;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/comm" })
public class CommController {
	@Autowired
	@Qualifier(value = "commService")
	private CommService commService;

	// 发布界面缓存图片
	@RequestMapping(value = "uploadImg")
	@ResponseBody
	public ImgJson uploadImg(@RequestParam(value = "imgUpload", required = false) MultipartFile imgFile,
			HttpServletRequest request, HttpSession session) {
		// 封装接收的数据
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		// 返回给View的数据
		ImgJson jsonToView = new ImgJson();

		// 图片文件
		msgToService.setJsonData("imgFile", imgFile);
		// 图片显示位置
		msgToService.setRequestData("imgPath", request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath());
		// 图片存储位置
		msgToService.setRequestData("savePath", request.getSession().getServletContext().getRealPath("/"));
		// 存入session中图片的名字
		msgToService.setSessionData("imgName", session.getAttribute("imgName"));
		// 执行
		MsgJson<String, Object> msgFromService = commService.uploadImg(msgToService);
		// 执行状态
		if (msgFromService.getState()) {
			// 上传成功
			MyMsgJson.setData(msgFromService, session);
			jsonToView.setImgName((String) msgFromService.getJsonData("imgName"));
			jsonToView.setImgUrl((String) msgFromService.getJsonData("imgUrl"));
			jsonToView.setState(msgFromService.getState());
			jsonToView.setMsg((String) msgFromService.getMsg());
		} else {
			// 上传失败
			jsonToView.setState(msgFromService.getState());
			jsonToView.setMsg((String) msgFromService.getMsg());
		}
		return jsonToView;
	}

	// 发布
	@RequestMapping(value = "releaseComm")
	@ResponseBody
	public MsgJson<String, Object> releaseComm(@RequestBody ReleaseJson releaseJson, HttpServletRequest request) {

		// 初始化传递给Service的数据
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		// 初始化传给View的数据
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();

		// 发布表单
		msgToService.setJsonData("releaseJson", releaseJson);
		// 图片储存位置
		msgToService.setRequestData("savePath", request.getSession().getServletContext().getRealPath("/"));
		// 图片缓存位置
		msgToService.setRequestData("cachePath", request.getSession().getServletContext().getRealPath("/"));
		// 进行操作
		MsgJson<String, Object> msgFromService = commService.releaseComm(msgToService);
		// 操作状态
		if (msgFromService.getState()) {
			// 操作成功 跳转到已发布页面
			msgToView.setJsonData("page", "/shareOnline/main/turn/released");
		} else {
			// 操作失败 返回发布页面
			msgToView.setJsonData("page", "/shareOnline/main/turn/release");
		}
		// 状态消息
		msgToView.setMsg(msgFromService.getMsg());
		return msgToView;
	}

	// 修改
	@RequestMapping(value = "reviseComm")
	@ResponseBody
	public MsgJson<String, Object> reviseComm(@RequestBody ReviseJson reviseJson, HttpServletRequest request,HttpSession session) {

		// 初始化传递给Service的数据
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		// 初始化传给View的数据
		MsgJson<String, Object> msgToView = MyMsgJson.newMsgjson();

		// 修改的表单
		msgToService.setJsonData("reviseJson", reviseJson);
		// 图片储存位置
		msgToService.setRequestData("savePath", request.getSession().getServletContext().getRealPath("/"));
		// 图片缓存位置
		msgToService.setRequestData("cachePath", request.getSession().getServletContext().getRealPath("/"));
		// 进行操作
		MsgJson<String, Object> msgFromService = commService.reviseComm(msgToService);
		// 操作状态
		if (msgFromService.getState()) {
			// 操作成功 跳转到已发布页面
			msgToView.setJsonData("page", "/shareOnline/main/turn/released");
		} else {
			// 操作失败 返回发布页面
			msgToView.setJsonData("page", "/shareOnline/main/turn/revise");
		}
		MyMsgJson.setData(msgToView, session);
		// 状态消息
		msgToView.setMsg(msgFromService.getMsg());
		return msgToView;
	}

	// 已发布商品的列表
	@RequestMapping(value = { "getReleasedComm" })
	@ResponseBody
	public MsgJson<String, Object> releasedComm(HttpSession session, HttpServletRequest request) {
		// 初始化传入服务层的数据
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		// 传入图片的显示路径
		msgToService.setRequestData("imgPath", request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath());
		// 获取登录用户的信息
		msgToService.setSessionData("user", session.getAttribute("user"));
		MsgJson<String, Object> msgFromService = commService.getReleasedComm(msgToService);
		return msgFromService;
	}

	// 删除商品
	@RequestMapping(value = "delComm")
	@ResponseBody
	public ModelAndView delComm(int commId, HttpServletRequest request,HttpSession session) {
		// 初始化传入服务层是数据
		MsgJson<String, Object> msgToService = MyMsgJson.newMsgjson();
		// 图片储存位置
		msgToService.setRequestData("savePath", request.getSession().getServletContext().getRealPath("/"));
		// 用户状态
		msgToService.setRequestData("commId", commId);
		msgToService.setSessionData("user", session.getAttribute("user"));
		msgToService.setSessionData("isLogin", session.getAttribute("isLogin"));
		MsgJson<String, Object> msgFromService = commService.dropComm(msgToService);
		ModelAndView mv = new ModelAndView();
		// 删除成功 返回已发布页面
		if(msgFromService.getState())
			mv.setViewName("released");
		else 
			mv.setViewName("index");
		
		return mv;
	}

	// 获得账号类型列表
	@RequestMapping(value = "getReleaseType")
	@ResponseBody
	public List<Type> getReleaseType() {

		// 从Service传来的数据
		MsgJson<String, Object> msgFromService = MyMsgJson.newMsgjson();
		// 初始化传给View的数据
		msgFromService = commService.getAllTypeList();
		List<Type> jsonToView = (List<Type>) msgFromService.getJsonData("typeList");

		return jsonToView;
	}
}

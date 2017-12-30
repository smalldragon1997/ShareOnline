// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 18:59:15
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CommServiceImpl.java

package com.share.serviceImpl;

import com.share.dao.*;
import com.share.entity.*;
import com.share.json.*;
import com.share.service.CommService;
import com.share.tools.*;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service(value = "commService")
public class CommServiceImpl implements CommService {

	@Autowired
	private CommDao commDao;
	@Autowired
	private ReleaseDao releaseDao;
	@Autowired
	private CommTypeDao commTypeDao;
	@Autowired
	private TypeDao typeDao;

	// 图片缓存
	@Override
	public MsgJson<String, Object> uploadImg(MsgJson<String, Object> msgFromController) {
		// 初始化传给控制层的数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 接收图片
		MultipartFile imgFile = (MultipartFile) msgFromController.getJsonData("imgFile");
		if (imgFile == null) {
			msgToController.setMsg("图片上传失败！111");
			msgToController.setState(false);
			return msgToController;
		}
		// 目标图片等会存储的路径
		File targetFile = null;
		// 图片显示在网页显示的路径
		String imgUrl = "";
		// 获取图片文件名加后缀
		String imgName = imgFile.getOriginalFilename();

		// 如果图片文件不是非法的 进行路径读取 存储操作
		if (imgName != null && imgName != "") {
			// 图片显示位置
			String imgPath = (String) msgFromController.getRequestData("imgPath") + "/images/uploadCache/";
			// 接收图片储存位置
			String savePath = (String) msgFromController.getRequestData("savePath") + "images/uploadCache";
			// 获取图片文件后缀
			String fileSuffix = imgName.substring(imgName.lastIndexOf("."), imgName.length());
			// 是否已经上传过图片
			if (msgFromController.getSessionData("imgName") == null) {
				System.out.println("图片不存在哦");
				msgFromController.setSessionData("imgName",
						new Date().getTime() + "_" + new Random().nextInt(1000) + fileSuffix);
			}else {

				System.out.println("图片已经存在哦"+msgFromController.getSessionData("imgName"));
			}
			// 新的图片文件名
			imgName = msgFromController.getSessionData("imgName").toString();

			// 判断图片是否已存在
			targetFile = new File(savePath, imgName);
			if (targetFile.exists()) {
				// 如果存在文件 则删除
				targetFile.delete();
			}
			targetFile = new File(savePath, imgName);

			try {
				// 储存图片
				imgFile.transferTo(targetFile);
				// 设置网页显示的src路径
				imgUrl = imgPath + imgName;
				// 封装返回给控制层的数据
				msgToController.setJsonData("imgUrl", imgUrl);
				msgToController.setSessionData("imgName", imgName);
				msgToController.setJsonData("imgName", imgName);
				msgToController.setMsg("图片上传成功！");

			} catch (IllegalStateException | IOException e) {
				msgToController.setMsg("图片上传失败！");
				msgToController.setState(false);
				e.printStackTrace();
			}
		} else {
			msgToController.setMsg("图片上传失败！文件错误");
			msgToController.setState(false);
		}
		return msgToController;
	}

	// 发布商品
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MsgJson<String, Object> releaseComm(MsgJson<String, Object> msgFromController) {
		// 初始化返回控制层的数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		msgToController.setMsg("发布成功！");
		// 获得控制层传来的表单数据
		ReleaseJson releaseFromController = (ReleaseJson) msgFromController.getJsonData("releaseJson");
		// 测试是否非法数据
		if (releaseFromController == null) {
			msgToController.setState(false);
			msgToController.setMsg("接收数据发布表单为空！");
			return msgToController;
		}
		// 取得表单的每个值
		int userId = releaseFromController.getUserId();
		int typeId = releaseFromController.getTypeId();
		String info = releaseFromController.getIntroduce();
		int price = releaseFromController.getCommPrice();
		Timestamp startTime = MyTimestramp
				.setTime(MyTimestramp.changeToTimestramp(releaseFromController.getStartTime()));
		Timestamp endTime = MyTimestramp.setTime(MyTimestramp.changeToTimestramp(releaseFromController.getEndTime()));
		String title = releaseFromController.getCommTitle();
		String img = releaseFromController.getImgName();
		// 插入comm表
		Comm comm = new Comm(0, releaseFromController.getCommTitle());
		if (commDao.insertComm(comm) == 1) {
			// 插入comm成功
			// 更新released表
			Timestamp releaseTime = MyTimestramp.setTime(new Timestamp(System.currentTimeMillis()));
			Release release = new Release(comm.getCommId(), userId, info, price, startTime, endTime, releaseTime, 0,
					title, img);
			release.setState(1); // 1代表发布状态 2代表已售
			if (releaseDao.insertRelease(release) != 1) {
				// 插入
				msgToController.setState(false);
				msgToController.setMsg("插入released失败！");
				return msgToController;
			} else {
				// 更新comm_type表
				CommType commType = new CommType(comm.getCommId(), typeId);
				if (commTypeDao.insertCommType(commType) != 1) {
					msgToController.setState(false);
					msgToController.setMsg("插入comm_type失败！");
					return msgToController;
				} else {
					// 如果有选择图片
					if (img.length() != 0) {
						// 把img存入comm文件夹
						String savePath = (String) msgFromController.getRequestData("savePath") + "images/comm/";
						String cachePath = (String) msgFromController.getRequestData("cachePath")
								+ "images/uploadCache";
						File file = new File(cachePath, img);
						if (!MyFile.copyFileToDir(file, savePath + img)) {
							msgToController.setState(false);
							msgToController.setMsg("图片复制失败！请重新上传图片");
							return msgToController;
						}
					}
					// 删除sessoin中的图片名称
					msgToController.setSessionData("remove", "imgName");
				}
			}

		} else {
			// 插入comm失败
			msgToController.setState(false);
			msgToController.setMsg("插入comm失败");
			return msgToController;
		}

		return msgToController;
	}

	// 删除商品
	public MsgJson<String, Object> dropComm(MsgJson<String, Object> msgFromController) {

		// 初始化返回控制层的数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 判断是否已登录
		if ("yes".equals(msgFromController.getSessionData("isLogin"))) {
			// 获得 commId 和user
			int commId = (int) msgFromController.getRequestData("commId");
			int userId = ((User) msgFromController.getSessionData("user")).getUserId();
			// 获得该用户的所有已发布
			List<Release> releases = releaseDao.getAllReleaseByUserId(userId);
			boolean flag = false;
			for (int i = 0; i < releases.size(); i++) {
				// 如果其中有匹配的商品 则进行删除操作 否则返回主页重新登录
				if (releases.get(i).getCommId() == commId) {
					String typeName = typeDao.getTypeByTypeId((commTypeDao.getCommTypeByCommId(commId)).getTypeId()).getTypeName();
					flag = true;
					break;
				}
			}
			if (!flag) {
				// 操作异常 重新登录
				msgToController.setSessionData("remove", "user");
				msgToController.setSessionData("remove", "isLogin");
				msgToController.setJsonData("page", "index");
				msgToController.setState(false);
				return msgToController;
			}
			// comm文件夹
			String savePath = (String) msgFromController.getRequestData("savePath") + "images/comm";
			// 获取图片名称
			String imgName = releaseDao.getReleaseByCommId(commId).getImg();
			// 判断图片是否已存在
			File targetFile = new File(savePath, imgName);
			if (targetFile.exists()) {
				System.out.println(targetFile.getName());
				// 如果存在文件 则删除
				targetFile.delete();
			}
			if (commDao.deleteComm(commId) != 1) {
				System.out.println("失败啦");
				msgToController.setState(false);
				return msgToController;
			}
			if (releaseDao.deleteRelease(commId) != 1) {
				msgToController.setState(false);
				return msgToController;
			}
			if (commTypeDao.deleteCommType(commId) != 1) {
				msgToController.setState(false);
				return msgToController;
			}
		} else {
			msgToController.setJsonData("page", "index");
			msgToController.setState(false);
			return msgToController;
		}

		return msgToController;
	}

	// 修改商品
	public MsgJson<String, Object> reviseComm(MsgJson<String, Object> msgFromController) {
		// 初始化返回数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		msgToController.setMsg("修改成功");
		// 获得修改过的releaseJson
		ReviseJson jsonFromController = (ReviseJson) msgFromController.getJsonData("reviseJson");
		// 如果提交空则返回
		if (jsonFromController == null) {
			msgToController.setState(false);
			msgToController.setMsg("请重新提交");
			return msgToController;
		}
		// 修改的信息
		int userId = jsonFromController.getUserId();
		String info = jsonFromController.getIntroduce();
		int price = jsonFromController.getCommPrice();
		Timestamp startTime = MyTimestramp.setTime(MyTimestramp.changeToTimestramp(jsonFromController.getStartTime()));
		Timestamp endTime = MyTimestramp.setTime(MyTimestramp.changeToTimestramp(jsonFromController.getEndTime()));
		String title = jsonFromController.getCommTitle();
		String img = jsonFromController.getImgName();
		Comm comm = new Comm(jsonFromController.getCommId(), jsonFromController.getCommTitle());
		// 销毁sessoin的commId
		msgToController.setSessionData("remove", "commId");
		// 更新comm表
		if (commDao.updateComm(comm) == 1) {
			Timestamp releaseTime = MyTimestramp.setTime(new Timestamp(System.currentTimeMillis()));
			Release release = new Release(comm.getCommId(), userId, info, price, startTime, endTime, releaseTime, 0,
					title, img);
			release.setState(1);
			// 删除以前的图片
			String oldImg = releaseDao.getReleaseByCommId(comm.getCommId()).getImg();
			// 如果老图片存在 则删除
			if (oldImg.length() != 0) {
				String savePath = (String) msgFromController.getRequestData("savePath") + "images/comm/";
				MyFile.deleteFile(savePath + oldImg);
			}
			// 更新released表
			if (releaseDao.updateRelease(release) != 1) {
				msgToController.setState(false);
				msgToController.setMsg("更新released失败");
				return msgToController;
			}
			// 如果有选择图片
			if (img.length() != 0) {
				// 把img存入comm文件夹
				String savePath = (String) msgFromController.getRequestData("savePath") + "images/comm/";
				String cachePath = (String) msgFromController.getRequestData("cachePath") + "images/uploadCache";
				File file = new File(cachePath, img);
				if (!MyFile.copyFileToDir(file, savePath + img)) {
					msgToController.setState(false);
					msgToController.setMsg("图片复制失败！请重新上传图片");
					return msgToController;
				}
			}
			// 删除sessoin中的图片名称
			msgToController.setSessionData("remove", "imgName");
		} else {
			msgToController.setState(false);
			msgToController.setMsg("comm表更新失败");
		}
		return msgToController;
	}

	// 获得所有类型的集合
	@Override
	public MsgJson<String, Object> getAllTypeList() {
		// 初始化返回控制层的封装数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 进行操作
		List<Type> typeList = typeDao.getAllTypeList();
		// 下标为3 封装的的自定义数据 这里数据为轮播图的list
		msgToController.setJsonData("typeList", typeList);

		return msgToController;
	}

	// 获得已发布商品
	public MsgJson<String, Object> getReleasedComm(MsgJson<String, Object> msgFromController) {
		// 获得控制的数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取用户信息
		User user = (User) msgFromController.getSessionData("user");
		// 获取用户发布的所有release并且状态为已发布
		List<Release> releases = releaseDao.getAllReleaseByUserIdAndState(user.getUserId(), 1);
		// 用来储存最终获取到的商品json集合
		List<CommJson> commJsons = new ArrayList<CommJson>();
		// 如果此用户没有发布的商品那么返回
		if (releases.isEmpty()) {
			msgToController.setMsg("你还没有发布过商品哦...");
			msgToController.setState(false);
			return msgToController;
		}
		// 遍历获取到的release
		for (int i = 0; i < releases.size(); i++) {
			// 获得commid
			int commId = ((Release) releases.get(i)).getCommId();
			// 根据commid获取商品类型
			CommType commType = commTypeDao.getCommTypeByCommId(commId);
			// 根据类型id获取类型
			Type type = typeDao.getTypeByTypeId(commType.getTypeId());
			// 根据commid获取商品 需要更改 重复商品名
			Comm comm = commDao.getCommByCommId(commId);
			// 有效时间
			String availableTime = MyTimestramp.changeToShowString(((Release) releases.get(i)).getStartTime()) + " 至 "
					+ MyTimestramp.changeToShowString(((Release) releases.get(i)).getEndTime());
			// 编辑时间
			String releaseTime = MyTimestramp.changeToShowString(((Release) releases.get(i)).getReleaseTime());
			// 价格
			int price = releases.get(i).getPrice();
			// 图片显示路径
			String img = releases.get(i).getImg();
			if (img.length() != 0)
				img = msgFromController.getRequestData("imgPath") + "/images/comm/" + img;
			else
				img = msgFromController.getRequestData("imgPath") + "/images/type/" + commType.getTypeId() + ".jpg";
			// 封装
			CommJson commJson = new CommJson(type.getTypeName(), comm.getCommName(), releaseTime, availableTime, img,
					price, commId,commType.getTypeId());
			// 添加到返回的集合中
			commJsons.add(commJson);
		}
		// 封装集合到返回的数据中
		msgToController.setJsonData("comms", commJsons);
		return msgToController;
	}

	// 获得搜索的商品
	public MsgJson<String, Object> queryComm(MsgJson<String, Object> msgFromController){
		//初始化返回控制层的数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		//获得控制层传来的typeId和query
		int typeId = (int) msgFromController.getSessionData("typeId");
		String typeName = typeDao.getTypeByTypeId(typeId).getTypeName();
		String query = (String) msgFromController.getSessionData("query");
		
		//根据typeId 获得商品类型列表
		List<CommType> commTypes = commTypeDao.getCommTypeByTypeId(typeId);
		//初始化储存CommJson的列表
		List<CommJson> commJsons = new ArrayList<CommJson>();
		//如果query为空 那么久搜索相应的类型 如果非空 则搜索这个类别中商品title相应的关键字
		if(query.length()==0){
			//遍历商品类型列表
			for (int i = 0; i < commTypes.size(); i++) {
				//通过commId获得release
				Release release = releaseDao.getReleaseByCommId(commTypes.get(i).getCommId());
				String availableTime = release.getStartTime()+" 至 "+release.getEndTime();
				String releaseTime = release.getReleaseTime().toString();
				// 图片显示路径
				String img = release.getImg();
				if (img.length() != 0)
					img = msgFromController.getRequestData("imgPath") + "/images/comm/" + img;
				else
					img = msgFromController.getRequestData("imgPath") + "/images/type/" + commTypes.get(i).getTypeId() + ".jpg";
				//将数据封装到commJson
				CommJson commJson = new CommJson(typeName,release.getTitle(),releaseTime,availableTime,img,release.getPrice(),commTypes.get(i).getCommId(),typeId);
				//添加到commjsons
				commJsons.add(commJson);
			}
		}else{
			//遍历商品类型列表 搜索满足条件的商品
			List<Release> releases = releaseDao.getAllReleaseByQuery(query);
			for (int i = 0; i < releases.size(); i++) {
				Release release = releases.get(i);
				//通过commId获得release
				String availableTime = release.getStartTime()+" 至 "+release.getEndTime();
				String releaseTime = release.getReleaseTime().toString();
				// 图片显示路径
				String img = release.getImg();
				if (img.length() != 0)
					img = msgFromController.getRequestData("imgPath") + "/images/comm/" + img;
				else
					img = msgFromController.getRequestData("imgPath") + "/images/type/" + commTypes.get(i).getTypeId() + ".jpg";
				//将数据封装到commJson
				CommJson commJson = new CommJson(typeName,release.getTitle(),releaseTime,availableTime,img,release.getPrice(),releases.get(i).getCommId(),typeId);
				//添加到commjsons
				commJsons.add(commJson);
			}
			typeName = " 搜索结果";
		}
		if(commJsons.size()==0){
			msgToController.setMsg("当前查询无结果哦");
			msgToController.setState(false);
		}
		msgToController.setJsonData("typeName", typeName);
		msgToController.setJsonData("comms", commJsons);
		return  msgToController;
	}
}

// Decompiled by DJ v3.12.12.101 Copyright 2016 Atanas Neshkov  Date: 2017/12/22 星期五 19:00:44
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   IndexServiceImpl.java

package com.share.serviceImpl;

import com.share.dao.*;
import com.share.entity.*;
import com.share.json.CommJson;
import com.share.json.MsgJson;
import com.share.service.IndexService;
import com.share.tools.MyMsgJson;
import com.share.tools.MyTimestramp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

//service注解 spring扫描自动注册为bean
@Service("indexService")
public class IndexServiceImpl implements IndexService {

	@Autowired
	private TypeDao typeDao;
	@Autowired
	private CommDao commDao;
	@Autowired
	private ReleaseDao releaseDao;
	@Autowired
	private CommTypeDao commTypeDao;

	// 热门共享列表
	@Override
	public MsgJson<String, Object> getIndexHotList() {
		// 初始化返回控制层的封装数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取数据库中热门共享的结果集 放入封装数据中
		List<Type> hotList = typeDao.getTypeListByCount(0, 8);
		// 下标为3 封装的的自定义数据 这里数据为热门共享的list
		msgToController.setJsonData("hotList", hotList);
		return msgToController;
	}

	// 轮播图列表
	@Override
	public MsgJson<String, Object> getIndexSlideList() {
		// 初始化返回控制层的封装数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		// 获取数据库中轮播图的结果集 放入封装数据中
		List<Type> slideList = typeDao.getTypeListByCount(0, 8);
		// 下标为3 封装的的自定义数据 这里数据为轮播图的list
		msgToController.setJsonData("slideList", slideList);
		return msgToController;
	}

	// 获得首页的商品列表 需要重构
	public MsgJson<String, Object> getIndexCommList(MsgJson<String, Object> msgFromController) {
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();
		List<Type> types = typeDao.getTypeListByCount(0, 8);
		msgToController.setJsonData("commNameList", types);
		List<List<CommJson>> commTypeList = new ArrayList<List<CommJson>>();
		for (int i = 0; i < types.size(); i++) {
			List<CommType> commTypes = commTypeDao.getCommTypeByTypeId(types.get(i).getTypeId());
			List<Comm> comms = new ArrayList<Comm>();
			for (int j = 0; j < commTypes.size(); j++)
				comms.add(commDao.getCommByCommId(((CommType) commTypes.get(j)).getCommId()));

			List<CommJson> commJsons = new ArrayList<CommJson>();
			for (int j = 0; j < comms.size(); j++) {
				if (j == 4)
					break;
				int commId = ((Comm) comms.get(j)).getCommId();
				Release release = releaseDao.getReleaseByCommId(commId);
				Comm comm = commDao.getCommByCommId(commId);
				String availableTime = (new StringBuilder(
						String.valueOf(MyTimestramp.changeToShowString(release.getStartTime())))).append(" \u81F3 ")
								.append(MyTimestramp.changeToShowString(release.getEndTime())).toString();
				String releaseTime = MyTimestramp.changeToShowString(release.getReleaseTime());
				int price = release.getPrice();
				// 图片显示路径
				String img = release.getImg();
				if (img.length() != 0)
					img = msgFromController.getRequestData("imgPath") + "/images/comm/" + img;
				else
					img = msgFromController.getRequestData("imgPath") + "/images/type/" + types.get(i).getTypeId()
							+ ".jpg";
				CommJson commJson = new CommJson(((Type) types.get(i)).getTypeName(), comm.getCommName(), releaseTime,
						availableTime, img, price, commId,types.get(i).getTypeId());
				commJsons.add(commJson);
			}

			commTypeList.add(commJsons);
		}

		msgToController.setJsonData("commList", commTypeList);
		return msgToController;
	}

	// 进入修改页面
	@Override
	public MsgJson<String, Object> reviseComm(MsgJson<String, Object> msgFromController) {
		// 初始化返回控制层的封装数据
		MsgJson<String, Object> msgToController = MyMsgJson.newMsgjson();

		// 判断是否已登录
		if ("yes".equals(msgFromController.getSessionData("isLogin"))) {
			//获得 commId 和user
			int commId = (int) msgFromController.getRequestData("commId");
			int userId = ((User) msgFromController.getSessionData("user")).getUserId();
			// 获得该用户的所有已发布
			List<Release> releases = releaseDao.getAllReleaseByUserId(userId);
			boolean flag = false;
			for (int i = 0; i < releases.size(); i++) {
				// 如果其中有匹配的商品 则返回修改页面的请求
				if (releases.get(i).getCommId() == commId) {
					String typeName = typeDao.getTypeByTypeId((commTypeDao.getCommTypeByCommId(commId)).getTypeId()).getTypeName();
					// 设置类型名和commid
					msgToController.setSessionData("typeName", typeName);
					msgToController.setSessionData("commId", commId);
					msgToController.setJsonData("page", "revise");
					System.out.println(1);
					flag = true;
					break;
				}
			}
			if(!flag){
				//操作异常 重新登录
				msgToController.setSessionData("remove", "user");
				msgToController.setSessionData("remove", "isLogin");
				msgToController.setJsonData("page", "index");
				msgToController.setState(false);
				System.out.println(2);
			}
		} else {
			msgToController.setJsonData("page", "index");
			msgToController.setState(false);
			System.out.println(3);
		}

		return msgToController;
	}

}

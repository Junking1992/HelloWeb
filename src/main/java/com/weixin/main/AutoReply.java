package com.weixin.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.weixin.security.AesException;
import com.weixin.security.EncryptUtil;
import com.weixin.security.WXBizMsgCrypt;

public abstract class AutoReply {

	/**
	 * 实现类需初始化的参数
	 */
	protected static String TOKEN, ENCODINGAESKEY, APPID;

	/**
	 * 所有类型消息
	 */
	private static final String TEXT = "text";
	private static final String IMAGE = "image";
	private static final String VOICE = "voice";
	private static final String VIDEO = "video";
	private static final String SHORTVIDEO = "shortvideo";
	private static final String LOCATION = "location";
	private static final String LINK = "link";

	private HttpServletRequest httpServletRequest = null;
	private Element root = null;

	/**
	 * 构造函数初始化
	 */
	public AutoReply() {
		init();
	}

	/**
	 * 自动消息回复，支持7种类型消息
	 * 
	 * @param request
	 * @return 回复消息
	 * @author W11821
	 * @date 2016年9月9日 下午6:22:56
	 */
	public synchronized String reply(HttpServletRequest request) {
		try {
			// 解密、解析
			root = DocumentHelper.parseText(decoderXML(request)).getRootElement();
			httpServletRequest = request;
			// 请求控制
			return replyController(root);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 解密
	 * 
	 * @param request
	 * @return
	 * @author W11821
	 * @date 2016年9月9日 下午4:06:02
	 */
	private String decoderXML(HttpServletRequest request) {
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			// 1.从request中取得输入流
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			StringBuffer buf = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				buf.append(line);
			}
			// 2.解密
			WXBizMsgCrypt wxCeypt = new WXBizMsgCrypt(TOKEN, ENCODINGAESKEY, APPID);
			return wxCeypt.decryptMsg(request.getParameter("msg_signature"), request.getParameter("timestamp"), request.getParameter("nonce"), buf.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AesException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	/**
	 * 加密
	 * 
	 * @param request
	 * @param replyMsg
	 * @return
	 * @author W11821
	 * @date 2016年9月9日 下午4:05:35
	 */
	private String encoderXML(HttpServletRequest request, String replyMsg) {
		try {
			WXBizMsgCrypt wxCeypt = new WXBizMsgCrypt(TOKEN, ENCODINGAESKEY, APPID);
			return wxCeypt.encryptMsg(replyMsg, request.getParameter("timestamp"), request.getParameter("nonce"));
		} catch (AesException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 消息回复控制器
	 * 
	 * @param root
	 * @return
	 * @author W11821
	 * @date 2016年9月9日 下午6:19:09
	 */
	private String replyController(Element root) {
		String msg = "";
		switch (root.element("MsgType").getText()) {
		case TEXT:
			msg = doText(root.element("Content").getText());
			break;
		case IMAGE:
			msg = doImage(root.element("PicUrl").getText(), root.element("MediaId").getText());
			break;
		case VOICE:
			msg = doVoice(root.element("MediaId").getText(), root.element("Format").getText());
			break;
		case VIDEO:
			msg = doVideo(root.element("MediaId").getText(), root.element("ThumbMediaId").getText());
			break;
		case SHORTVIDEO:
			msg = shortvideo(root);
			break;
		case LOCATION:
			msg = location(root);
			break;
		case LINK:
			msg = link(root);
			break;
		default:
			break;
		}
		return msg;
	}

	/**
	 * 接入微信验证
	 * 
	 * @param request
	 * @return
	 * @author W11821
	 * @date 2016年9月9日 下午6:20:21
	 */
	public String authentication(HttpServletRequest request) {
		// 1.将token、timestamp、nonce三个参数进行字典序排序
		List<String> list = new ArrayList<String>();
		list.add(TOKEN);
		list.add(request.getParameter("timestamp"));
		list.add(request.getParameter("nonce"));
		Collections.sort(list);

		// 2.将三个参数字符串拼接成一个字符串进行sha1加密
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append(str);
		}
		// 3.比较加密后的字串和signature是否一致，一致返回echostr
		if (EncryptUtil.sha1Encode(sb.toString()).equals(request.getParameter("signature"))) {
			return request.getParameter("echostr");
		}
		return "";
	}

	/**
	 * 文本消息
	 */
	public String text(String content) {
		Document xml = DocumentHelper.createDocument();
		Element returnRoot = xml.addElement("xml");
		returnRoot.addElement("ToUserName").addCDATA(root.element("FromUserName").getText());
		returnRoot.addElement("FromUserName").addCDATA(root.element("ToUserName").getText());
		returnRoot.addElement("CreateTime").addCDATA((System.currentTimeMillis() + "").substring(0, 10));
		returnRoot.addElement("MsgType").addCDATA(TEXT);
		returnRoot.addElement("Content").addCDATA(content);
		return encoderXML(httpServletRequest, returnRoot.asXML());
	}

	/**
	 * 图片消息
	 */
	public String image(String mediaId) {
		Document xml = DocumentHelper.createDocument();
		Element returnRoot = xml.addElement("xml");
		returnRoot.addElement("ToUserName").addCDATA(root.element("FromUserName").getText());
		returnRoot.addElement("FromUserName").addCDATA(root.element("ToUserName").getText());
		returnRoot.addElement("CreateTime").addCDATA((System.currentTimeMillis() + "").substring(0, 10));
		returnRoot.addElement("MsgType").addCDATA(IMAGE);
		returnRoot.addElement("Image").addElement("MediaId").addCDATA(mediaId);
		return encoderXML(httpServletRequest, returnRoot.asXML());
	}

	/**
	 * 语音消息
	 */
	public String voice(String mediaId) {
		Document xml = DocumentHelper.createDocument();
		Element returnRoot = xml.addElement("xml");
		returnRoot.addElement("ToUserName").addCDATA(root.element("FromUserName").getText());
		returnRoot.addElement("FromUserName").addCDATA(root.element("ToUserName").getText());
		returnRoot.addElement("CreateTime").addCDATA((System.currentTimeMillis() + "").substring(0, 10));
		returnRoot.addElement("MsgType").addCDATA(VOICE);
		returnRoot.addElement("Voice").addElement("MediaId").addCDATA(mediaId);
		return encoderXML(httpServletRequest, returnRoot.asXML());
	}

	/**
	 * 视频消息
	 */
	public String video(String mediaId, String title, String description) {
		Document xml = DocumentHelper.createDocument();
		Element returnRoot = xml.addElement("xml");
		returnRoot.addElement("ToUserName").addCDATA(root.element("FromUserName").getText());
		returnRoot.addElement("FromUserName").addCDATA(root.element("ToUserName").getText());
		returnRoot.addElement("CreateTime").addCDATA((System.currentTimeMillis() + "").substring(0, 10));
		returnRoot.addElement("MsgType").addCDATA(VIDEO);
		Element videoElement = returnRoot.addElement("Video");
		videoElement.addElement("MediaId").addCDATA(mediaId);
		videoElement.addElement("Title").addCDATA(title);
		videoElement.addElement("Description").addCDATA(description);
		return encoderXML(httpServletRequest, returnRoot.asXML());
	}

	private String shortvideo(Element root) {
		// TODO Auto-generated method stub
		return null;
	}

	private String location(Element root) {
		// TODO Auto-generated method stub
		return null;
	}

	private String link(Element root) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 初始化:TOKEN, ENCODINGAESKEY, APPID
	 * 
	 * @author W11821
	 * @date 2016年9月9日 下午6:20:31
	 */
	public abstract void init();

	/**
	 * 处理文本消息
	 * 
	 * @param content
	 *            文本消息内容
	 * @return
	 * @author W11821
	 * @date 2016年9月9日 下午5:53:06
	 */
	public abstract String doText(String content);

	/**
	 * 处理图片消息
	 * 
	 * @param picUrl
	 *            图片链接（由系统生成）
	 * @param mediaId
	 *            图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 * @return
	 * @author W11821
	 * @date 2016年9月9日 下午6:36:23
	 */
	public abstract String doImage(String picUrl, String mediaId);

	/**
	 * 处理语音消息
	 * 
	 * @param mediaId
	 *            语音消息媒体id ，可以调用多媒体文件下载接口拉取数据。
	 * @param format
	 *            语音格式，如amr，speex等
	 * @return
	 * @author W11821
	 * @date 2016年9月9日 下午6:50:38
	 */
	public abstract String doVoice(String mediaId, String format);
	
	public abstract String doVideo(String mediaId, String thumbMediaId);
	
}

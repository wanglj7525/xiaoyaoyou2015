package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import message.resp.Article;
import message.resp.NewsMessage;
import message.resp.TextMessage;
import util.MessageUtil;

/**
 * @ClassName: CoreService
 * @Description: 核心服务类
 * @author wlj
 * @date 2015-12-17 下午04:39:12
 * 
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息！";
				String content = requestMap.get("Content");
				// 判断用户发送的是否是单个QQ表情
				if (isQqFace(content)) {
					// 用户发什么QQ表情，就返回什么QQ表情
					respContent = content;
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				} else {
					if("?".equals(content)||"？".equals(content)){
						respContent = getMainMenu();
						textMessage.setContent(respContent);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					}
					// 创建图文消息
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setFuncFlag(0);

					List<Article> articleList = new ArrayList<Article>();
					// 单图文消息
					if ("1".equals(content)) {
						Article article = new Article();
						article.setTitle("烟台中科网络技术研究所");
						article.setDescription("烟台中科网络技术研究所！");
						article.setPicUrl("http://www.int-yt.com/u/cms/www/201410/23093406vitx.jpg");
						article.setUrl("http://www.int-yt.com/");
						articleList.add(article);
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息包含的图文集合
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换成xml字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					// 单图文消息---不含图片
					else if ("2".equals(content)) {
						Article article = new Article();
						article.setTitle("烟台中科网络技术研究所");
						// 图文消息中可以使用QQ表情、符号表情
						article.setDescription("烟台中科网络技术研究所，"
								+ emoji(0x1F6B9));
						// 将图片置为空
						article.setPicUrl("");
						article.setUrl("http://www.int-yt.com/");
						articleList.add(article);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					// 多图文消息
					else if ("3".equals(content)) {
						Article article1 = new Article();
						article1.setTitle("多图文消息\n引言");
						article1.setDescription("");
						article1.setPicUrl("http://www.int-yt.com/u/cms/www/201410/23093406vitx.jpg");
						article1.setUrl("http://www.int-yt.com/report/649.jhtml");

						Article article2 = new Article();
						article2.setTitle("第2篇\n2014年威海荣成秋游之旅");
						article2.setDescription("");
						article2.setPicUrl("http://www.int-yt.com/u/cms/www/201409/17112138n6iw.jpg");
						article2.setUrl("http://www.int-yt.com/staff/614.jhtml");

						Article article3 = new Article();
						article3.setTitle("第3篇\n网络所2015年春游-杭州、乌镇、台儿庄之行");
						article3.setDescription("");
						article3.setPicUrl("http://www.int-yt.com/u/cms/www/201506/12104411fait.jpg");
						article3.setUrl("http://www.int-yt.com/staff/654.jhtml");

						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					// 多图文消息---首条消息不含图片
					else if ("4".equals(content)) {
						Article article1 = new Article();
						article1.setTitle("烟台中科网络技术研究所");
						article1.setDescription("");
						// 将图片置为空
						article1.setPicUrl("");
						article1.setUrl("http://www.int-yt.com/");

						Article article2 = new Article();
						article2.setTitle("第4篇\n长城战略咨询来所调研");
						article2.setDescription("");
						article2.setPicUrl("http://www.int-yt.com/u/cms/www/201511/11110755ruu0.jpg");
						article2.setUrl("http://www.int-yt.com/report/664.jhtml");

						Article article3 = new Article();
						article3.setTitle("第5篇\n网络所IT沙龙大数据系列活动开讲");
						article3.setDescription("");
						article3.setPicUrl("http://www.int-yt.com/u/cms/www/201304/281552151k6t.jpg");
						article3.setUrl("http://www.int-yt.com/cooperate/465.jhtml");

						Article article4 = new Article();
						article4.setTitle("第6篇\n中国科学院网络数据科学与技术重点实验室揭牌仪式在计算所举行");
						article4.setDescription("");
						article4.setPicUrl("http://www.int-yt.com/u/cms/www/201312/10163800dj7v.jpg");
						article4.setUrl("http://www.int-yt.com/hoinc/559.jhtml");

						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						articleList.add(article4);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					// 多图文消息---最后一条消息不含图片
					else if ("5".equals(content)) {
						Article article1 = new Article();
						article1.setTitle("第7篇\n数据、工具、人员和决心，重新定义大数据行业的机会");
						article1.setDescription("");
						article1.setPicUrl("http://www.int-yt.com/u/cms/www/201403/05140730aqim.png");
						article1.setUrl("http://www.int-yt.com/hoinc/586.jhtml");

						Article article2 = new Article();
						article2.setTitle("第8篇\n网络所“2014年度总结表彰大会”圆满召开");
						article2.setDescription("");
						article2.setPicUrl("http://www.int-yt.com/u/cms/www/201502/09153730lpdk.jpg");
						article2.setUrl("http://www.int-yt.com/staff/642.jhtml");

						Article article3 = new Article();
						article3.setTitle("招聘职位！");
						article3.setDescription("");
						// 将图片置为空
						article3.setPicUrl("");
						article3.setUrl("http://www.int-yt.com/zhaopin/index.jhtml");

						articleList.add(article1);
						articleList.add(article2);
						articleList.add(article3);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);

					}
				}

			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
				}
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}// 自定义菜单点击事件  
            else if (msgType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                // 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                String eventKey = requestMap.get("EventKey");  

                if (eventKey.equals("11")) {  
                    respContent = "天气预报菜单项被点击！";  
                } else if (eventKey.equals("12")) {  
                    respContent = "公交查询菜单项被点击！";  
                } else if (eventKey.equals("13")) {  
                    respContent = "周边搜索菜单项被点击！";  
                } else if (eventKey.equals("14")) {  
                    respContent = "历史上的今天菜单项被点击！";  
                } else if (eventKey.equals("21")) {  
                    respContent = "歌曲点播菜单项被点击！";  
                } else if (eventKey.equals("22")) {  
                    respContent = "经典游戏菜单项被点击！";  
                } else if (eventKey.equals("23")) {  
                    respContent = "美女电台菜单项被点击！";  
                } else if (eventKey.equals("24")) {  
                    respContent = "人脸识别菜单项被点击！";  
                } else if (eventKey.equals("25")) {  
                    respContent = "聊天唠嗑菜单项被点击！";  
                } else if (eventKey.equals("31")) {  
                    respContent = "Q友圈菜单项被点击！";  
                } else if (eventKey.equals("32")) {  
                    respContent = "电影排行榜菜单项被点击！";  
                } else if (eventKey.equals("33")) {  
                    respContent = "幽默笑话菜单项被点击！";  
                } else if (eventKey.equals("34")) {  
                	respContent = "用户反馈菜单项被点击！";  
                } else if (eventKey.equals("35")) {  
                	respContent = "关于我们菜单项被点击！";  
                }  
            }  


			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

	/**
	 * 判断是否是QQ表情
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;

		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
	
	/** 
	 * 主菜单 
	 *  
	 * @return 
	 */  
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("您好，请回复数字选择服务：").append("\n\n");  
	    buffer.append("1  ").append("\n");  
	    buffer.append("2 ").append("\n");  
	    buffer.append("3  ").append("\n");  
	    buffer.append("4  ").append("\n");  
	    buffer.append("5  ").append("\n\n");  
	    buffer.append("回复“?”显示此帮助菜单");  
	    return buffer.toString();  
	}  

}

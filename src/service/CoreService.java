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
 * @Description: ���ķ�����
 * @author wlj
 * @date 2015-12-17 ����04:39:12
 * 
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";

			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "�����͵����ı���Ϣ��";
				String content = requestMap.get("Content");
				// �ж��û����͵��Ƿ��ǵ���QQ����
				if (isQqFace(content)) {
					// �û���ʲôQQ���飬�ͷ���ʲôQQ����
					respContent = content;
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				} else {
					if("?".equals(content)||"��".equals(content)){
						respContent = getMainMenu();
						textMessage.setContent(respContent);
						respMessage = MessageUtil.textMessageToXml(textMessage);
					}
					// ����ͼ����Ϣ
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setFuncFlag(0);

					List<Article> articleList = new ArrayList<Article>();
					// ��ͼ����Ϣ
					if ("1".equals(content)) {
						Article article = new Article();
						article.setTitle("��̨�п����缼���о���");
						article.setDescription("��̨�п����缼���о�����");
						article.setPicUrl("http://www.int-yt.com/u/cms/www/201410/23093406vitx.jpg");
						article.setUrl("http://www.int-yt.com/");
						articleList.add(article);
						// ����ͼ����Ϣ����
						newsMessage.setArticleCount(articleList.size());
						// ����ͼ����Ϣ������ͼ�ļ���
						newsMessage.setArticles(articleList);
						// ��ͼ����Ϣ����ת����xml�ַ���
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					// ��ͼ����Ϣ---����ͼƬ
					else if ("2".equals(content)) {
						Article article = new Article();
						article.setTitle("��̨�п����缼���о���");
						// ͼ����Ϣ�п���ʹ��QQ���顢���ű���
						article.setDescription("��̨�п����缼���о�����"
								+ emoji(0x1F6B9));
						// ��ͼƬ��Ϊ��
						article.setPicUrl("");
						article.setUrl("http://www.int-yt.com/");
						articleList.add(article);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
					// ��ͼ����Ϣ
					else if ("3".equals(content)) {
						Article article1 = new Article();
						article1.setTitle("��ͼ����Ϣ\n����");
						article1.setDescription("");
						article1.setPicUrl("http://www.int-yt.com/u/cms/www/201410/23093406vitx.jpg");
						article1.setUrl("http://www.int-yt.com/report/649.jhtml");

						Article article2 = new Article();
						article2.setTitle("��2ƪ\n2014�������ٳ�����֮��");
						article2.setDescription("");
						article2.setPicUrl("http://www.int-yt.com/u/cms/www/201409/17112138n6iw.jpg");
						article2.setUrl("http://www.int-yt.com/staff/614.jhtml");

						Article article3 = new Article();
						article3.setTitle("��3ƪ\n������2015�괺��-���ݡ�����̨��ׯ֮��");
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
					// ��ͼ����Ϣ---������Ϣ����ͼƬ
					else if ("4".equals(content)) {
						Article article1 = new Article();
						article1.setTitle("��̨�п����缼���о���");
						article1.setDescription("");
						// ��ͼƬ��Ϊ��
						article1.setPicUrl("");
						article1.setUrl("http://www.int-yt.com/");

						Article article2 = new Article();
						article2.setTitle("��4ƪ\n����ս����ѯ��������");
						article2.setDescription("");
						article2.setPicUrl("http://www.int-yt.com/u/cms/www/201511/11110755ruu0.jpg");
						article2.setUrl("http://www.int-yt.com/report/664.jhtml");

						Article article3 = new Article();
						article3.setTitle("��5ƪ\n������ITɳ��������ϵ�л����");
						article3.setDescription("");
						article3.setPicUrl("http://www.int-yt.com/u/cms/www/201304/281552151k6t.jpg");
						article3.setUrl("http://www.int-yt.com/cooperate/465.jhtml");

						Article article4 = new Article();
						article4.setTitle("��6ƪ\n�й���ѧԺ�������ݿ�ѧ�뼼���ص�ʵ���ҽ�����ʽ�ڼ���������");
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
					// ��ͼ����Ϣ---���һ����Ϣ����ͼƬ
					else if ("5".equals(content)) {
						Article article1 = new Article();
						article1.setTitle("��7ƪ\n���ݡ����ߡ���Ա�;��ģ����¶����������ҵ�Ļ���");
						article1.setDescription("");
						article1.setPicUrl("http://www.int-yt.com/u/cms/www/201403/05140730aqim.png");
						article1.setUrl("http://www.int-yt.com/hoinc/586.jhtml");

						Article article2 = new Article();
						article2.setTitle("��8ƪ\n��������2014����ܽ���ô�ᡱԲ���ٿ�");
						article2.setDescription("");
						article2.setPicUrl("http://www.int-yt.com/u/cms/www/201502/09153730lpdk.jpg");
						article2.setUrl("http://www.int-yt.com/staff/642.jhtml");

						Article article3 = new Article();
						article3.setTitle("��Ƹְλ��");
						article3.setDescription("");
						// ��ͼƬ��Ϊ��
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
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע��";
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ
				}
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}// �Զ���˵�����¼�  
            else if (msgType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                // �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ  
                String eventKey = requestMap.get("EventKey");  

                if (eventKey.equals("11")) {  
                    respContent = "����Ԥ���˵�������";  
                } else if (eventKey.equals("12")) {  
                    respContent = "������ѯ�˵�������";  
                } else if (eventKey.equals("13")) {  
                    respContent = "�ܱ������˵�������";  
                } else if (eventKey.equals("14")) {  
                    respContent = "��ʷ�ϵĽ���˵�������";  
                } else if (eventKey.equals("21")) {  
                    respContent = "�����㲥�˵�������";  
                } else if (eventKey.equals("22")) {  
                    respContent = "������Ϸ�˵�������";  
                } else if (eventKey.equals("23")) {  
                    respContent = "��Ů��̨�˵�������";  
                } else if (eventKey.equals("24")) {  
                    respContent = "����ʶ��˵�������";  
                } else if (eventKey.equals("25")) {  
                    respContent = "������ྲ˵�������";  
                } else if (eventKey.equals("31")) {  
                    respContent = "Q��Ȧ�˵�������";  
                } else if (eventKey.equals("32")) {  
                    respContent = "��Ӱ���а�˵�������";  
                } else if (eventKey.equals("33")) {  
                    respContent = "��ĬЦ���˵�������";  
                } else if (eventKey.equals("34")) {  
                	respContent = "�û������˵�������";  
                } else if (eventKey.equals("35")) {  
                	respContent = "�������ǲ˵�������";  
                }  
            }  


			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

	/**
	 * �ж��Ƿ���QQ����
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;

		// �ж�QQ�����������ʽ
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

	/**
	 * emoji����ת��(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
	
	/** 
	 * ���˵� 
	 *  
	 * @return 
	 */  
	public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("���ã���ظ�����ѡ�����").append("\n\n");  
	    buffer.append("1  ").append("\n");  
	    buffer.append("2 ").append("\n");  
	    buffer.append("3  ").append("\n");  
	    buffer.append("4  ").append("\n");  
	    buffer.append("5  ").append("\n\n");  
	    buffer.append("�ظ���?����ʾ�˰����˵�");  
	    return buffer.toString();  
	}  

}

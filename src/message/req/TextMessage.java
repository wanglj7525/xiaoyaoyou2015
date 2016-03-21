package message.req;

/**
 * @ClassName: TextMessage
 * @Description: 文本消息 
 * @author wlj
 * @date 2015-12-17 下午04:07:42
 *
 */ 
public class TextMessage extends BaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}

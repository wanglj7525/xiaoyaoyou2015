package message.resp;

/**
 * @ClassName: TextMessage
 * @Description: �ı���Ϣ 
 * @author wlj
 * @date 2015-12-17 ����04:07:42
 *
 */ 
public class TextMessage extends BaseMessage {
	// �ظ�����Ϣ����
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
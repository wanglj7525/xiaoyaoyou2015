package message.req;

/**
 * @ClassName: LinkMessage
 * @Description: ������Ϣ 
 * @author wlj
 * @date 2015-12-17 ����04:11:15
 *
 */ 
public class LinkMessage extends BaseMessage {

	// ��Ϣ����
	private String Title;
	// ��Ϣ����
	private String Description;
	// ��Ϣ����
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}

	
}

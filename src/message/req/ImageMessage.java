package message.req;

/**
 * @ClassName: ImageMessage
 * @Description: ͼƬ��Ϣ 
 * @author wlj
 * @date 2015-12-17 ����04:08:31
 *
 */ 
public class ImageMessage extends BaseMessage{
	//ͼƬ����
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}

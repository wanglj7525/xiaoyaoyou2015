package message.req;

/**
 * @ClassName: ImageMessage
 * @Description: 图片消息 
 * @author wlj
 * @date 2015-12-17 下午04:08:31
 *
 */ 
public class ImageMessage extends BaseMessage{
	//图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}

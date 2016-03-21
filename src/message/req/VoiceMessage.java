package message.req;

/**
 * @ClassName: VoiceMessage
 * @Description: 音频消息 
 * @author wlj
 * @date 2015-12-17 下午04:12:10
 *
 */ 
public class VoiceMessage extends BaseMessage {
	// 媒体ID
	private String MediaId;
	// 语音格式
	private String Format;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}

}

package pojo;

/**
 * @ClassName: AccessToken
 * @Description: 微信通用接口凭证
 * @author wlj
 * @date 2015-12-21 上午10:56:31
 *
 */ 
public class AccessToken {
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

}

package message.resp;

import java.util.List;

/**
 * @ClassName: NewsMessage
 * @Description: �ı���Ϣ
 * @author wlj
 * @date 2015-12-17 ����04:19:52
 *
 */ 
public class NewsMessage extends BaseMessage {
	// ͼ����Ϣ����������Ϊ10������
	private int ArticleCount;
	// ����ͼ����Ϣ��Ϣ��Ĭ�ϵ�һ��itemΪ��ͼ
	private List<Article> Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

	
}

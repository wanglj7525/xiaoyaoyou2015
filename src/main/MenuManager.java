package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.AccessToken;
import pojo.Button;
import pojo.CommonButton;
import pojo.ComplexButton;
import pojo.Menu;
import pojo.ViewButton;
import util.WeixinUtil;
  
/**
 * @ClassName: MenuManager
 * @Description: �˵���������
 * @author wlj
 * @date 2015-12-21 ����03:56:56
 *
 */ 
public class MenuManager {  
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);  
  
    public static void main(String[] args) {  
        // �������û�Ψһƾ֤  
        String appId = "wx8c6ce3ae6aa1ba86";  
        // �������û�Ψһƾ֤��Կ  
        String appSecret = "e9691d77fe165544176db3779dcbe5f7 ";  
  
        // ���ýӿڻ�ȡaccess_token  
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
  
        if (null != at) {  
            // ���ýӿڴ����˵�  
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());  
  
            // �жϲ˵��������  
            if (0 == result)  
                log.info("�˵������ɹ���");  
            else  
                log.info("�˵�����ʧ�ܣ������룺" + result);  
        }  
    }  
  
    /** 
     * ��װ�˵����� 
     *  
     * @return 
     */  
    private static Menu getMenu() {  
    	 CommonButton btn11 = new CommonButton();  
         btn11.setName("����Ԥ��");  
         btn11.setType("click");  
         btn11.setKey("11");  
   
         CommonButton btn12 = new CommonButton();  
         btn12.setName("������ѯ");  
         btn12.setType("click");  
         btn12.setKey("12");  
   
         CommonButton btn13 = new CommonButton();  
         btn13.setName("�ܱ�����");  
         btn13.setType("click");  
         btn13.setKey("13");  
   
         CommonButton btn14 = new CommonButton();  
         btn14.setName("��ʷ�ϵĽ���");  
         btn14.setType("click");  
         btn14.setKey("14");  
           
         CommonButton btn15 = new CommonButton();  
         btn15.setName("��Ӱ���а�");  
         btn15.setType("click");  
         btn15.setKey("32");  
   
         CommonButton btn21 = new CommonButton();  
         btn21.setName("�����㲥");  
         btn21.setType("click");  
         btn21.setKey("21");  
   
         CommonButton btn22 = new CommonButton();  
         btn22.setName("������Ϸ");  
         btn22.setType("click");  
         btn22.setKey("22");  
   
         CommonButton btn23 = new CommonButton();  
         btn23.setName("��Ů��̨");  
         btn23.setType("click");  
         btn23.setKey("23");  
   
         CommonButton btn24 = new CommonButton();  
         btn24.setName("����ʶ��");  
         btn24.setType("click");  
         btn24.setKey("24");  
   
         CommonButton btn25 = new CommonButton();  
         btn25.setName("�������");  
         btn25.setType("click");  
         btn25.setKey("25");  
   
         CommonButton btn31 = new CommonButton();  
         btn31.setName("Q��Ȧ");  
         btn31.setType("click");  
         btn31.setKey("31");  
   
         CommonButton btn33 = new CommonButton();  
         btn33.setName("��ĬЦ��");  
         btn33.setType("click");  
         btn33.setKey("33");  
           
         CommonButton btn34 = new CommonButton();  
         btn34.setName("�û�����");  
         btn34.setType("click");  
         btn34.setKey("34");  
           
         CommonButton btn35 = new CommonButton();  
         btn35.setName("��������");  
         btn35.setType("click");  
         btn35.setKey("35");  
           
         ViewButton btn32 = new ViewButton();  
         btn32.setName("ʹ�ð���");  
         btn32.setType("view");  
         btn32.setUrl("http://www.int-yt.com/");  
   
         ComplexButton mainBtn1 = new ComplexButton();  
         mainBtn1.setName("��������");  
         mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14, btn15 });  
   
         ComplexButton mainBtn2 = new ComplexButton();  
         mainBtn2.setName("������վ");  
         mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24, btn25 });  
   
         ComplexButton mainBtn3 = new ComplexButton();  
         mainBtn3.setName("����");  
         mainBtn3.setSub_button(new Button[] { btn31, btn33, btn34, btn35, btn32 });  
   
         /** 
          * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br> 
          *  
          * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br> 
          * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br> 
          * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
          */  
         Menu menu = new Menu();  
         menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
   
         return menu;  

    }  
}  

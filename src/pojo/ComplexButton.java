package pojo;

/**
 * @ClassName: ComplexButton
 * @Description: ���Ӱ�ť������ť��
 * @author wlj
 * @date 2015-12-21 ����11:13:24
 *
 */ 
public class ComplexButton extends Button {  
    private Button[] sub_button;  
  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}  

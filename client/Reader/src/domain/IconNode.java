package domain;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

//����ڵ���
public class IconNode extends DefaultMutableTreeNode {
	protected Icon icon;
	protected String txt;
	protected int leftFlag=0;
	protected int rightFlag=0;
	protected String father="";
	// ֻ���ı��Ľڵ㹹��
	public IconNode(String txt) {
		super();
		this.txt = txt;
	}

	// ���ı���ͼƬ�Ľڵ㹹��
	public IconNode(Icon icon, String txt) {
		super();
		this.icon = icon;
		this.txt = txt;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setText(String txt) {
		this.txt = txt;
	}

	public String getText() {
		return txt;
	}
	public int getLeftFlag() {
		return leftFlag;
	}

	public void setLeftFlag(int leftFlag) {
		this.leftFlag = leftFlag;
	}

	public int getRightFlag() {
		return rightFlag;
	}

	public void setRightFlag(int rightFlag) {
		this.rightFlag = rightFlag;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}
	
}
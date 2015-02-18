package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlToText {
	public String HtmlToTextAction(String inputString) {
		String htmlStr = inputString; // ��html��ǩ���ַ���
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		Pattern p_houhtml;
		Matcher m_houhtml;
		Pattern p_spe;
		Matcher m_spe;
		Pattern p_blank;
		Matcher m_blank;
		Pattern p_table;
		Matcher m_table;
		Pattern p_enter;
		Matcher m_enter;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			// ����script��������ʽ.
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			// ����style��������ʽ.
			String regEx_html = "<[^>]+>";
			// ����HTML��ǩ��������ʽ
			String regEx_houhtml = "/[^>]+>";
			// ����HTML��ǩ��������ʽ
			String regEx_spe = "\\&[^;]+;";
			// ����������ŵ�������ʽ
			String regEx_blank = " +";
			// �������ո��������ʽ
			String regEx_table = "\t+";
			// �������Ʊ����������ʽ
			String regEx_enter = "\n+";
			// �������س���������ʽ

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // ����script��ǩ

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // ����style��ǩ

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // ����html��ǩ

			p_houhtml = Pattern
					.compile(regEx_houhtml, Pattern.CASE_INSENSITIVE);
			m_houhtml = p_houhtml.matcher(htmlStr);
			htmlStr = m_houhtml.replaceAll(""); // ����html��ǩ

			p_spe = Pattern.compile(regEx_spe, Pattern.CASE_INSENSITIVE);
			m_spe = p_spe.matcher(htmlStr);
			htmlStr = m_spe.replaceAll(""); // �����������

			p_blank = Pattern.compile(regEx_blank, Pattern.CASE_INSENSITIVE);
			m_blank = p_blank.matcher(htmlStr);
			htmlStr = m_blank.replaceAll(" "); // ���˹���Ŀո�

			p_table = Pattern.compile(regEx_table, Pattern.CASE_INSENSITIVE);
			m_table = p_table.matcher(htmlStr);
			htmlStr = m_table.replaceAll(" "); // ���˹�����Ʊ��

			p_enter = Pattern.compile(regEx_enter, Pattern.CASE_INSENSITIVE);
			m_enter = p_enter.matcher(htmlStr);
			htmlStr = m_enter.replaceAll(" "); // ���˹�����Ʊ��

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// �����ı��ַ���
	}
}

package cn.net.wangsu.dhvideo;

import java.awt.Component;
import java.io.UnsupportedEncodingException;

import com.sun.jna.Native;

public class DHNetSDKUtil
{

	/**
	 * �ַ�����ת��Ϊ�ַ���
	 * 
	 * @param byteArray
	 *            �ַ�����
	 * @return �ַ���
	 */
	public static String DHByteArrayToString(byte[] byteArray)
	{
		// string result = Encoding.GetEncoding("gb2312").GetString(byteArray);
		// string result = Encoding.GetEncoding(936).GetString(byteArray);
		String result = new String(byteArray);
		return result;
	}

	/**
	 * �ַ�����ת��Ϊ�ַ���[���������ַ����ݳ���Ϊ16���ַ��������]
	 * 
	 * @param byteArray
	 *            �ַ�����
	 * @param formatStyle
	 *            �ַ�����ʽ[�����ִ�Сд]
	 *            IP1:IP��ַ�ĵ�һ����;IP2:IP��ַ�ڶ�����;IP3:IP��ַ�ĵ�������;IP4:IP��ַ�ĵ��Ĳ���
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String DHByteArrayToString(byte[] byteArray,
			String formatStyle, String sEncodeing)
			throws UnsupportedEncodingException
	{
		// string result = Encoding.GetEncoding("gb2312").GetString(byteArray);
		// string result = Encoding.GetEncoding(936).GetString(byteArray);
		if (sEncodeing.equals("")) sEncodeing = "gb2312";
		String result = new String(byteArray, sEncodeing);
		if (result.length() == 16)
		{
			String sPart1 = result.substring(0, 4);
			String sPart2 = result.substring(4, 4);
			String sPart3 = result.substring(8, 4);
			String sPart4 = result.substring(12, 4);
			String strTemp = formatStyle.toUpperCase();
			// IP��ַ��ʽ����
			if (strTemp.indexOf("IP1") != -1)
			{
				strTemp = strTemp.replace("IP1", Integer.parseInt(sPart1) + "");
			}
			if (strTemp.indexOf("IP2") != -1)
			{
				strTemp = strTemp.replace("IP2", Integer.parseInt(sPart2) + "");
			}
			if (strTemp.indexOf("IP3") != -1)
			{
				strTemp = strTemp.replace("IP3", Integer.parseInt(sPart3) + "");
			}
			if (strTemp.indexOf("IP4") != -1)
			{
				strTemp = strTemp.replace("IP4", Integer.parseInt(sPart4) + "");
			}
			result = strTemp;
		}
		return result;
	}

	/**
	 * �ַ���ת��Ϊ�ַ�����
	 * 
	 * @param strValue
	 * @param byteArry
	 * @return
	 */
	public static boolean DHStringToByteArry(String strValue, byte[] byteArry)
	{
		try
		{
			// byte[] byteTemp =
			// Encoding.GetEncoding("gb2312").GetBytes(strValue);
			byte[] byteTemp = strValue.getBytes();
			int maxLen = (byteTemp.length > byteArry.length ? byteArry.length
					: byteTemp.length);
			for (int i = 0; i < byteArry.length; i++)
			{
				if (i < maxLen)
				{
					byteArry[i] = byteTemp[i];
				}
				else
				{
					byteArry[i] = 0;
				}
			}
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}

	/**
	 * �������ת��Ϊ��׼���Ĵ�����Ϣ����
	 * 
	 * @param errorCode
	 *            �������
	 * @return ��׼��������Ϣ����
	 */
	public static String VIDEOGetLastErrorName(int errorCode)
	{
		switch (errorCode)
		{
			case 0x80000000 | 1:
				return "Windowsϵͳ����";
			case 0x80000000 | 2:
				return "������󣬿�������Ϊ���糬ʱ";
			case 0x80000000 | 3:
				return "�豸Э�鲻ƥ��";
			case 0x80000000 | 4:
				return "�����Ч";
			case 0x80000000 | 5:
				return "��ͨ��ʧ��";
			case 0x80000000 | 6:
				return "�ر�ͨ��ʧ��";
			case 0x80000000 | 7:
				return "�û��������Ϸ�";
			case 0x80000000 | 8:
				return "SDK��ʼ������";
			case 0x80000000 | 9:
				return "SDK�������";
			case 0x80000000 | 10:
				return "����render��Դ����";
			case 0x80000000 | 11:
				return "�򿪽�������";
			case 0x800000 | 12:
				return "�رս�������";
			case 0x80000000 | 13:
				return "�໭��Ԥ���м�⵽ͨ����Ϊ0";
			case 0x80000000 | 14:
				return "¼�����ʼ��ʧ��";
			case 0x80000000 | 15:
				return "¼����δ����ʼ��";
			case 0x80000000 | 16:
				return "������Ƶ���ݳ���";
			case 0x80000000 | 17:
				return "ʵʱ�����Ѿ����ڱ���״̬";
			case 0x80000000 | 18:
				return "δ����ʵʱ����";
			case 0x80000000 | 19:
				return "���ļ�����";
			case 0x80000000 | 20:
				return "������̨���ƶ�ʱ��ʧ��";
			case 0x80000000 | 21:
				return "�Է������ݵ�У�����";
			case 0x80000000 | 22:
				return "û���㹻�Ļ���";
			case 0x80000000 | 23:
				return "��ǰSDKδ֧�ָù���";
			case 0x80000000 | 24:
				return "��ѯ����¼��";
			case 0x80000000 | 25:
				return "�޲���Ȩ��";
			case 0x80000000 | 26:
				return "��ʱ�޷�ִ��";
			case 0x80000000 | 27:
				return "δ���ֶԽ�ͨ��";
			case 0x80000000 | 28:
				return "δ������Ƶ";
			case 0x80000000 | 29:
				return "CLientSDKδ����ʼ��";
			case 0x80000000 | 30:
				return "�����ѽ���";
			case 0x80000000 | 31:
				return "��ѯ���Ϊ��";
			case 0x80000000 | 32:
				return "��ȡ����ʧ��λ�ã�ϵͳ����";
			case 0x80000000 | 33:
				return "��ȡ����ʧ��λ�ã����к�";
			case 0x80000000 | 34:
				return "��ȡ����ʧ��λ�ã���������";
			case 0x80000000 | 35:
				return "��ȡ����ʧ��λ�ã�DSP��������";
			case 0x80000000 | 36:
				return "��ȡ����ʧ��λ�ã���������";
			case 0x80000000 | 37:
				return "��ȡ����ʧ��λ�ã�ͨ������";
			case 0x80000000 | 38:
				return "��ȡ����ʧ��λ�ã���Ƶ����";
			case 0x80000000 | 39:
				return "��ȡ����ʧ��λ�ã�¼��ʱ����";
			case 0x80000000 | 40:
				return "��ȡ����ʧ��λ�ã�������Э������";
			case 0x80000000 | 41:
				return "��ȡ����ʧ��λ�ã�232���ڹ�������";
			case 0x80000000 | 42:
				return "��ȡ����ʧ��λ�ã�����������";
			case 0x80000000 | 43:
				return "��ȡ����ʧ��λ�ã�232��������";
			case 0x80000000 | 44:
				return "ȡ����ʧ��λ�ã��ⲿ������������";
			case 0x80000000 | 45:
				return "��ȡ����ʧ��λ�ã�ͼ���ⱨ������";
			case 0x80000000 | 46:
				return "��ȡ����ʧ��λ�ã��豸ʱ��";
			case 0x80000000 | 47:
				return "��ȡ����ʧ��λ�ã�Ԥ������";
			case 0x80000000 | 48:
				return "��ȡ����ʧ��λ�ã��Զ�ά������";
			case 0x80000000 | 49:
				return "��ȡ����ʧ��λ�ã���Ƶ��������";
			case 0x80000000 | 55:
				return "��������ʧ��λ�ã���������";
			case 0x80000000 | 56:
				return "��������ʧ��λ�ã���������";
			case 0x80000000 | 57:
				return "��������ʧ��λ�ã�ͨ������";
			case 0x80000000 | 58:
				return "��������ʧ��λ�ã���Ƶ����";
			case 0x80000000 | 59:
				return "��������ʧ��λ�ã�¼��ʱ����";
			case 0x80000000 | 60:
				return "��������ʧ��λ�ã�����������";
			case 0x80000000 | 61:
				return "��������ʧ��λ�ã�232��������";
			case 0x80000000 | 62:
				return "��������ʧ��λ�ã��ⲿ������������";
			case 0x80000000 | 63:
				return "��������ʧ��λ�ã�ͼ���ⱨ������";
			case 0x80000000 | 64:
				return "��������ʧ��λ�ã��豸ʱ��";
			case 0x80000000 | 65:
				return "��������ʧ��λ�ã�Ԥ������";
			case 0x80000000 | 66:
				return "��������ʧ��λ�ã��Զ�ά������";
			case 0x80000000 | 67:
				return "��������ʧ��λ�ã���Ƶ��������";
			case 0x80000000 | 70:
				return "��Ƶ����ӿ�û�гɹ���ʼ��";
			case 0x80000000 | 71:
				return "���ݹ���";
			case 0x80000000 | 72:
				return "�豸��֧�ָò���";
			case 0x80000000 | 73:
				return "�豸��Դ����";
			case 0x80000000 | 74:
				return "�������Ѿ�����";
			case 0x80000000 | 75:
				return "��������δ�ɹ�����";
			case 0x80000000 | 80:
				return "�������к�����";
			case 0x80000000 | 100:
				return "���벻��ȷ";
			case 0x80000000 | 101:
				return "�ʻ�������";
			case 0x80000000 | 102:
				return "�ȴ���¼���س�ʱ";
			case 0x80000000 | 103:
				return "�ʺ��ѵ�¼";
			case 0x80000000 | 104:
				return "�ʺ��ѱ�����";
			case 0x80000000 | 105:
				return "�ʺ��ѱ���Ϊ������";
			case 0x80000000 | 106:
				return "��Դ���㣬ϵͳæ";
			case 0x80000000 | 107:
				return "��������ʧ��";
			case 0x80000000 | 108:
				return "��������ʧ��";
			case 0x80000000 | 120:
				return "Render�����Ƶ����";
			case 0x80000000 | 121:
				return "Render��ر���Ƶ����";
			case 0x80000000 | 122:
				return "Render�������������";
			case 0x80000000 | 123:
				return "Render�����û����������";
			case 0x80000000 | 124:
				return "Render����ͣ���ų���";
			case 0x80000000 | 125:
				return "Render��ץͼ����";
			case 0x80000000 | 126:
				return "Render�ⲽ������";
			case 0x80000000 | 127:
				return "Render������֡�ʳ���";
			case 0x80000000 | 999:
				return "��ʱ�޷�����";
			case 0x80000000 | 1000:
				return "�������ݲ��Ϸ�";
			default:
				return "δ֪����";
		}
	}

	/**
	 * RealPlay�İ�װ�����ܿؼ��ģ��Կؼ��������ʽ���á�
	 * ע�⣺�������ؼ���java���Ƶģ��޷��õ����������swing�Ŀؼ��ᱨ����ʾ�������������ؼ�
	 * 
	 * @param lLoginID
	 * @param nChannelID
	 * @param c
	 *            �̳���awt�Ŀؼ�
	 * @return ͬDHNetSDK.CLIENT_RealPlay(...)
	 */
	public static int CLIENT_RealPlay(int lLoginID, int nChannelID, Component c)
	{
		return DHNetSDK.INSTANCE.CLIENT_RealPlay(lLoginID, nChannelID,
				Native.getComponentID(c));
	}

}

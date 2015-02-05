package cn.net.wangsu.dhvideo;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.*;

/**
 * @author ����
 * @time 2013-1-22
 *       ����˵��:��netsdk��dll��java���ã�����jna�������������jna.jar�ſ�ʹ�ã���Ӧjni�汾δ��������дһ��c
 *       ++�����е����� version:0.1 ��������˲��źͶϿ�
 */
public interface DHNetSDK extends Library
{
	/**
	 * ʵ�������еĹ��ܵ��ö�Ҫͨ����
	 */
	DHNetSDK INSTANCE = (DHNetSDK) Native.loadLibrary("dhnetsdk",
			DHNetSDK.class);

	static boolean initialized = false;

	/**
	 * ���߻ص��Ķ���ָ��ӿڣ������ṩ���߻ص�,Long����Ҫд��NativeLong
	 */
	public static interface fDisConnect extends Callback
	{
		/**
		 * �豸�Ķ��߻ص�
		 * 
		 * @param lLoginID
		 *            �豸id
		 * @param ip
		 *            �豸ip
		 * @param port
		 *            �豸�˿�
		 * @param dwUser
		 *            �豸�û� 0
		 * @return
		 */
		int invoke(Long lLoginID, String ip, Long port, short dwUser);
	}

	/**
	 * �ص������Ķ���ָ��ӿڵ�ʵ�֣��˴������ã������ڳ���������
	 * 
	 * @author ���� ͨ�����߻ص�����������ʵ��һ��������ã���dll������������ͨ���������ж��������Ȳ���
	 */
	public static class fDisConnect_real implements fDisConnect
	{
		@Override
		public int invoke(Long lLoginID, String ip, Long port, short dwUser)
		{
			// TODO Auto-generated method stub
			System.out.println("�����ˣ���");
			return 0;
		}

	}

	/**
	 * �豸��ʼ��
	 * 
	 * @param fdisconnect
	 *            ���߻ص�������ʵ��
	 * @param dwUser
	 *            �û���
	 * @return �ɹ�Ϊtrue��ʧ��Ϊfalse
	 */
	public boolean CLIENT_Init(fDisConnect fdisconnect, short dwUser);

	/**
	 * �豸��¼
	 * 
	 * @param pchDVRIP
	 *            ip��ַ
	 * @param wDVRPort
	 *            �˿ں�
	 * @param pchUserName
	 *            �û���
	 * @param pchPassword
	 *            ����
	 * @param lpDeviceInfo
	 *            �豸��Ϣ
	 * @param error
	 *            ����Ŵ����� ������ 1 ���벻��ȷ 2 �û��������� 3 ��¼��ʱ 4 �ʺ��ѵ�¼ 5 �ʺ��ѱ����� 6
	 *            �ʺű���Ϊ������ 7 ��Դ���㣬ϵͳæ 8 ������ʧ�� 9 ������ʧ�� 10 ��������û�������
	 * @return ʧ�ܷ���0���ɹ������豸ID����¼�ɹ�֮����豸�Ĳ���������ͨ����ֵ(�豸���)��Ӧ����Ӧ���豸��
	 *         ��ʾ���ڳ�ʼ����Ϳ��Ե��ñ��ӿ�ע�ᵽָ�����豸���ɹ��󽫷����豸���������صĺ������á��������ȳ�ʼ����
	 */
	public int CLIENT_Login(String pchDVRIP, int wDVRPort, String pchUserName,
			String pchPassword, NET_DEVICEINFO.ByReference lpDeviceInfo,
			int error);

	/**
	 * �豸
	 * 
	 * @author ���� ����DVRType��Ӧ��Ӧ�Ĺ��ܣ�����󻪵�skd�����ĵ�
	 */
	public static class NET_DEVICEINFO extends Structure
	{
		public byte[] sSerialNumber = new byte[48];
		public byte byAlarmInPortNum;// �����������
		public byte byAlarmOutPortNum;// ���������ʽ
		public byte byDiskNum;// Ӳ�̸���
		public byte byDVRType;// ���ͣ�����󻪿����ĵ���DHDEV_SYSTEM_ATTR_CFG
		public byte byChanNum;// ͨ������

		/**
		 * Structure ���������ڲ��ӿ� Structure.ByReference �� Structure.ByValue��
		 * �������ӿڽ����Ǳ�ǡ� ���һ����ʵ�� Structure.ByReference �ӿڣ� �ͱ�ʾ��������ṹ��ָ�� �� ���һ����ʵ��
		 * Structure.ByValue �ӿڣ��ͱ�ʾ��������ṹ�屾��
		 * ʾ����û���ҵ������ʵ�ֵ����ӣ�Ӧ���������ڸ��Ӹ��ӵĽӿ����ʱ����õĵ�
		 * 
		 * @author Administrator
		 * 
		 */
		public static class ByReference extends NET_DEVICEINFO implements
				Structure.ByReference
		{}

		public static class ByValue extends NET_DEVICEINFO implements
				Structure.ByValue
		{}

		@Override
		/**
		 * ����������ǽṹ���и����ֶε�˳��
		 */
		protected List getFieldOrder()
		{
			// TODO Auto-generated method stub
			return Arrays.asList(new String[] { "sSerialNumber",
					"byAlarmInPortNum", "byAlarmOutPortNum", "byDiskNum",
					"byDVRType", "byChanNum" });
			// return null;
		}
	}

	/**
	 * ʵʱ��ع��ܣ�hwndΪ���ھ���������long com.sun.jna.Native.getComponentID(Component c)�õ�
	 * 
	 * @param lLoginID
	 * @param nChannelID
	 * @param hWnd
	 * @return
	 */
	public int CLIENT_RealPlay(int lLoginID, int nChannelID, long hWnd);

	/**
	 * ֹͣʵʱ��أ������豸ͨ����ΪCLIENT_RealPlay���ص�id
	 * 
	 * @param lRealHandle
	 * @return
	 */
	public boolean CLIENT_StopRealPlay(int lRealHandle);

	/**
	 * ֹͣʵʱ��أ������豸ͨ����ΪCLIENT_RealPlay���ص�id
	 * 
	 * @param lRealHandle
	 * @return
	 */
	public boolean CLIENT_StopRealPlayEx(int lRealHandle);

	/**
	 * ���SDK, �ͷ�ռ�õ���Դ�������е�SDK����֮�����
	 */
	public void CLIENT_Cleanup();

	/**
	 * ע���豸�û���
	 * 
	 * @param lLoginID
	 *            �豸�û���¼ʱ�õ��ķ���ֵ
	 * @return true:�ɹ�;false:ʧ��
	 */
	public boolean CLIENT_Logout(int lLoginID);

	/**
	 * ���غ���ִ��ʧ�ܴ���
	 * 
	 * @return
	 */
	public int CLIENT_GetLastError();

	/**
	 * �������豸�����ӵȴ�ʱ��
	 * 
	 * @param nWaitTime
	 *            ���ӵȴ�ʱ��[��λ:����]
	 * @param nTryTimes
	 *            ���Ӵ���
	 */
	public void CLIENT_SetConnectTime(int nWaitTime, int nTryTimes);

	/**
	 * ��֪�������û���ã���ûд �����豸��Ϣ�ص�����, �����õ��豸��ǰ״̬��Ϣ
	 * 
	 * @param o
	 * @param dwUser
	 */
	public void CLIENT_SetDVRMessCallBack(Object o, int dwUser);

	/**
	 * ������������, Ŀǰֻʵ���˱�����������
	 * 
	 * @param wPort
	 *            ���������Ķ˿�
	 * @param pIp
	 *            �󶨵�IP��ΪNULLʱ�󶨱������кϷ�IP
	 * @param pfscb
	 *            ����������Ϣ�ص��ӿ�
	 * @param dwTimeOut
	 *            ������ά���� �ӵĳ�ʱʱ��
	 * @param dwUserData
	 *            �û��ص����Զ�������
	 * @return �ɹ����ط����������ʧ�ܷ���0
	 */
	public int CLIENT_StartService(int wPort, String pIp, Object pfscb,
			int dwTimeOut, int dwUserData);

	/**
	 * ֹͣ�˿ڼ�������
	 * 
	 * @param lHandle
	 *            Ҫ�رյķ������ľ��,CLIENT_StartService�ķ���ֵ
	 * @return true:�ɹ�;false:ʧ��
	 */
	public boolean CLIENT_StopService(int lHandle);
}

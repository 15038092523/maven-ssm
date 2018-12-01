package cmcc.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cmcc.common.HttpRequest;

public class PessimisticLockTest {

	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);// ģ��5�˲��������û�Ǯ��

		for (int i = 0; i < 5; i++) {// ģ��5���û�
			AnalogUser analogUser = new AnalogUser("user" + i, "1", "1", "20.024",
					latch);
			analogUser.start();
		}
		latch.countDown();// �������pһ �����߳��ͷ� �������ʡ�
		System.out.println("����ģ���������  at " + sdf.format(new Date()));
	}
	
	static class AnalogUser extends Thread{
        String workerName;//ģ���û�����
        String openId;
        String openType;
        String amount;
        CountDownLatch latch;

        public AnalogUser(String workerName, String openId, String openType, String amount,
                CountDownLatch latch) {         
            this.workerName = workerName;
            this.openId = openId;
            this.openType = openType;
            this.amount = amount;
            this.latch = latch;
        }
        @Override
        public void run() {         
            try {  
                latch.await(); //һֱ������ǰ�̣߳�ֱ����ʱ����ֵΪ0  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }           
            post();//����post ����          
        } 

        public void post(){

            System.out.println("ģ���û��� "+workerName+" ��ʼ����ģ������  at "+sdf.format(new Date()));  

            String url = "http://localhost:8080/ol/account/update.do";
            String data="openId="+openId+"&openType="+openType+"&amount="+amount;

            String result = HttpRequest.sendPost(url, data);
            System.out.println("���������"+result);
            System.out.println("ģ���û��� "+workerName+" ģ���������  at "+sdf.format(new Date())); 

        }
    }
}

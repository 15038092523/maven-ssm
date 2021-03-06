package cmcc.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import com.cmcc.common.HttpRequest;

public class ConcurrentTest {

	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);// 模拟5人并发请求，用户钱包

		for (int i = 0; i < 2; i++) {// 模拟5个用户
			AnalogUser analogUser = new AnalogUser("user" + i, "1", "1", "20.024",
					latch);
			analogUser.start();
		}
		latch.countDown();// 计数器減一 所有线程释放 并发访问。
		System.out.println("所有模拟请求结束  at " + sdf.format(new Date()));
	}

	static class AnalogUser extends Thread{
        String workerName;//模拟用户姓名
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
                latch.await(); //一直阻塞当前线程，直到计时器的值为0  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }           
            post();//发送post 请求          
        } 

        public void post(){

            System.out.println("模拟用户： "+workerName+" 开始发送模拟请求  at "+sdf.format(new Date()));  

            String url = "http://localhost:8080/ol/account/walleroptimisticlock.do";
            String data="openId="+openId+"&openType="+openType+"&amount="+amount;

            String result = HttpRequest.sendPost(url, data);
            System.out.println("操作结果："+result);
            System.out.println("模拟用户： "+workerName+" 模拟请求结束  at "+sdf.format(new Date())); 

        }
    }
}

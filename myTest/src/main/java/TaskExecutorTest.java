import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

public class TaskExecutorTest {



    public static void main(String[] args) throws InterruptedException {
        //新建线程并执行任务类
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "：异步任务");
        }).start();
        Thread.sleep(3000);
    }
}

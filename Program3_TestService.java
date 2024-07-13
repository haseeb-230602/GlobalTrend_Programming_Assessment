import org.springframework.stereotype.Service;

@Service
public class TestService {

    @LogExecutionTime
    public void execute() throws InterruptedException {
        // Simulate method execution time
        Thread.sleep(2000);
    }
}

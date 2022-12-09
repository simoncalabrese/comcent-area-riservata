package comcent.controller;


import comcent.common.configuration.BaseApplication;
import org.springframework.boot.SpringApplication;

/**
 * Created by simon.calabrese on 31/10/2017.
 */
public class Main extends BaseApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Main.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                System.out.println("Shutdown hook ran!")
        ));

        while (true) {
            Thread.sleep(1000);
        }
    }
}

package nekomaid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("nekomaid.mapper")
@SpringBootApplication
public class NekoMaidApplication {

    public static void main(String[] args) {
        SpringApplication.run(NekoMaidApplication.class, args);
    }

}

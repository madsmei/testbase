package com.abc.webflux;

import com.abc.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description 响应式编程（reactive programming）是一种基于数据流（data stream）和变化传递（propagation of change）的声明式（declarative）的编程范式
 * @Author Mads
 * @Date 2020/6/12 17:21
 * @Version 1.0
 */
@RestController
@RequestMapping("webflux")
public class WebFluxController {


    /*****
     * @Author Mads
     * @Description 在 WebFlux 中，除了 Mono 外，还有一个 Flux，这哥俩均能充当响应式编程中发布者的角色，不同的是：
     *
     * Mono：返回 0 或 1 个元素，即单个对象。
     * Flux：返回 N 个元素，即 List 列表对象。
     * @Date 17:31 2020/6/12
     * @param
     **/
    @GetMapping("/user")
    public Mono<String> getUser() {
        return Mono.fromSupplier(() -> createStr());
    }

    // 阻塞5秒钟
    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        return "some string";
    }

    @GetMapping("/user1")
    public Flux<User> getUser1() {
        User user = new User();
        user.setName("犬小哈");


        int[] nums = {1, 2, 3};
        int sum2 = IntStream.of(nums).parallel().sum();
        System.out.println("结果为：" + sum2);


        return Flux.just(user);
    }

    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux() {
        Flux<String> result = Flux
                .fromStream(IntStream.range(1, 5).mapToObj(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    return "flux data--" + i;
                }));
        return result;
    }
}

package com.dmg.saa01helloworld.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatHelloController {

    /**
     * 文字使用chatModel
     * 图片使用ImageModel
     * 语音使用AudioModel
     */
    @Resource
    private ChatModel chatModel;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    /**
     * 普通 通用调用
     * @author zhum
     * @date 2026/1/20 9:55
     * @param msg
     * @return java.lang.String
     */
    @GetMapping("/hello/dochat")
    public String doChat(@RequestParam(name = "msg", defaultValue = "你是谁") String msg) {
        return chatModel.call(msg);
    }

    /**
     * 流式调用  出一点返回一点 而不是一直转圈等待全部完成才返回
     * @author zhum
     * @date 2026/1/20 9:55
     * @param msg
     * @return reactor.core.publisher.Flux<java.lang.String>
     */
    @GetMapping("/hello/streamchat")
    public Flux<String> stream(@RequestParam(name = "msg", defaultValue = "你是谁") String msg) {
        return chatModel.stream(msg);
    }
}

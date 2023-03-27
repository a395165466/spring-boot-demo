package com.github.zhangguqing;

import com.github.zhangguqing.service.CommonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/demo/test")
public class TestController extends BaseController {
    @Resource
    private CommonService commonService;

    @PostMapping( "/add")
    public String add(String addStr) {
        System.out.println("input is " + addStr);
        return null;
    }

    @GetMapping("/query")
    public List<String> query(String assignee) {
        System.out.println("query" + assignee);
        return null;
    }
}
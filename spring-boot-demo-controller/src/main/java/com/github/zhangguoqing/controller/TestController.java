package com.github.zhangguoqing.controller;

import com.github.zhangguoqing.api.common.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
    @Resource
    private CommonService commonService;

    @PostMapping( "/add")
    public String add(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");
        System.out.println("input is:" + orderId);
        return orderId;
    }

    @GetMapping("/query")
    public HttpServletResponse query(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");
        System.out.println("query is:" + orderId);

        commonService.query(orderId);
        return null;
    }

    @GetMapping("/startFlow")
    public HttpServletResponse startFlow(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");
        System.out.println("query is:" + orderId);

        commonService.startFlow();
        return null;
    }

    @GetMapping("/deploy")
    public HttpServletResponse deploy(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");
        System.out.println("query is:" + orderId);

        commonService.deploy();
        return null;
    }
}
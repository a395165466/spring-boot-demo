package com.github.zhangguoqing.service.simpleBPMN.model;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BaseElement {
    /**
     * 元素id
     */
    private String id;

    /**
     * 元素名称
     */
    private String name;

    /**
     * 属性
     */
    private Map<String, ExtensionAttribute> extensionAttributes = Maps.newHashMap();

    /**
     * 扩展元素
     */
    private Map<String, ExtensionElement> extensionElements = Maps.newHashMap();
}

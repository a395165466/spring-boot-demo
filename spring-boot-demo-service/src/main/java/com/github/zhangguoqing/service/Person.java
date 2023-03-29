package com.github.zhangguoqing.service;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;

    private Integer age;

    private String cardNumber;

    public void execute(String val) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name:").append(name)
                .append(",age:").append(age)
                .append(",cardNumber:").append(cardNumber);

        return stringBuilder.toString();
    }
}

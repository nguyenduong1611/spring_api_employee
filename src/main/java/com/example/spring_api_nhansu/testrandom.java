package com.example.spring_api_nhansu;

import org.apache.commons.codec.digest.DigestUtils;

public class testrandom {
    public static void main(String[] args) {
        String sha256hex = DigestUtils.sha256Hex("originalString");
        System.out.println(sha256hex);
    }


}

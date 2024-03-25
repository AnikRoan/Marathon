package com.example.marathon.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {
    /*
    @PreAuthorize()Метод будет вызван только в случае, если условие,
    заданное в @PreAuthorize, будет выполнено.
            @PostAuthorize()для выполнения после вызова метода

    @PreFilter()позволяет вам определить фильтр,
    который будет применен к списку аргументов метода перед его вызовом.
    @PostFilter()позволяет вам определить фильтр, который будет применен
     к списку возвращаемых значений метода после его вызова.
     */

    @GetMapping("/hello/{smth}")
    @PreAuthorize("@testEndpointAuthorizationManager.authoriz(#TestEndpointAuthorizationManager. #smth)")
    public String hello(@PathVariable String smth){
        return smth;
    }
    @GetMapping("/demo")
    public String demo(){
        return "DEMO";
    }
}

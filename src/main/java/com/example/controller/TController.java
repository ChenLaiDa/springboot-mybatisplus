package com.example.controller;


import com.example.common.reponse.BaseResult;
import com.example.model.entity.T;
import com.example.service.ITService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/t")
public class TController {
    @Resource
    private ITService itService;

    @GetMapping("/insert")
    public BaseResult<?> insert(){
        List<T> list = new ArrayList<>();

        for (int i = 1; i <= 100000 ; i++) {
            T t = new T();
            t.setA(i);
            t.setB(i);
            list.add(t);

        }
        itService.saveBatch(list);
        return BaseResult.success();

    }

}


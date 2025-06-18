package com.gec;

import com.gec.mongodb.MongoDBApp;
import com.gec.mongodb.model.MyUser;
import com.gec.mongodb.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = MongoDBApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest
{

    @Autowired
    private UserRepository userRepository;


    // 插入数据
    @Test
    public void test1(){

        MyUser myUser = new MyUser();
        myUser.setName("hauxiong2");
        myUser.setAge(20);
        myUser.setEmail("hauxiong2@qq.com");
        myUser.setCreateDate(new Date());



        this.userRepository.insert(myUser);

        System.out.println("插入数据完成..");

    }


    // 查询全部
    @Test
    public void test2(){
        List<MyUser> userlist = this.userRepository.findAll();
        if (userlist != null) {
            userlist.forEach(System.out::println);
        }

    }

    @Test
    /// 根据id去查询某一条数据
    public void test3(){

       // Object objid = new Object("678b0708de911b12bd2fb424");

        Optional<MyUser> optionalMyUser = this.userRepository.findById(new ObjectId("67d54c9c02f26d4ed73e9e22"));
        if (optionalMyUser.isPresent()){
            MyUser myUser = optionalMyUser.get();
            System.out.println(myUser);
        }

    }

    // 查询支持分页操作
    @Test
    public void test4(){

        // 分页必传参数   pageNum pageSize

       //  Pageable 接口  -》 PageRequest 实现类
       //  public static PageRequest of(int page, int size)


        // 分页的条件


        // page 参数 是从 0 开始

        Pageable pageable =  PageRequest.of(0, 2);

        MyUser myUser = new MyUser();

        // 精准匹配 （相等查询）
        //myUser.setName("hauxiong");

        //  static <T> Example<T> of(T probe)
        Example example = Example.of(myUser);


        Page<MyUser> page = this.userRepository.findAll(example, pageable);

        int pageNum = page.getNumber();
        System.out.println("当前页码:"+pageNum);
        int size = page.getSize();
        System.out.println("每页显示的条数:"+size);

        int totalPages = page.getTotalPages();
        System.out.println("获取总的页码："+totalPages);

        long totalElements = page.getTotalElements();
        System.out.println("获取总的记录数:"+totalElements);

        List<MyUser> myUsers = page.getContent();
        System.out.println("分页后的list集合数据");
        myUsers.forEach(System.out::println);


    }

}

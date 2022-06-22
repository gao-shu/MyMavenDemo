package com.example.springbootdemo1.stream;

import com.example.springbootdemo1.Entity.Person;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gaoshu
 * @describe GroupBy
 * @date 2022/06/22 16:59
 **/
public class GroupBy {

    // https://blog.csdn.net/pz5942/article/details/107161851/


    private List<Person> personList = getList();
    private static List<Person> getList(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person().setName("张三").setAge(6).setSex(0));
        personList.add(new Person().setName("李四").setAge(5).setSex(1));
        personList.add(new Person().setName("王五").setAge(7).setSex(0));
        personList.add(new Person().setName("王五").setAge(10).setSex(1));
        return personList;
    }


    @Test
    public void test(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person().setName("张三").setAge(6).setSex(0));
        personList.add(new Person().setName("李四").setAge(5).setSex(1));
        personList.add(new Person().setName("王五").setAge(7).setSex(0));
        personList.add(new Person().setName("王五").setAge(10).setSex(1));
        // 根据性别分组对象
        test1(personList);
        // 获取某个属性最大值
        test2(personList);
        // 获取属性map
        // test3(personList);
    }

    public void test1(List<Person> personList){
        Map<Integer, List<Person>> sexMap = personList.stream().collect(Collectors.groupingBy(person -> person.getSex()));
        System.out.println(sexMap);
    }

    public void test2(List<Person> personList){
        Map<Integer, List<String>> sexMap = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println(sexMap);
    }

    public void test20(List<Person> personList){
        Person person = personList.stream().max(Comparator.comparing(Person::getAge)).get();
        System.out.println(person);
    }

    public void test30(List<Person> personList){
        Map<String, Integer> map = personList.stream().collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println(map);
    }

    @Test
    public void test21(){
        // List<String> nameList = personList.stream().collect(Collectors.mapping(Person::getName, Collectors.toList()));
        System.out.println(personList);
        // List<String> name = personList.stream().collect(Collectors.mapping(Person::getName, Collectors.toList()));
        Set<String> name = personList.stream().collect(Collectors.mapping(Person::getName, Collectors.toSet()));
        System.out.println(name);
    }

}

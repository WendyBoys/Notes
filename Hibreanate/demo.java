package com.xuan.controller;


import com.xuan.domain.User;
import com.xuan.domain.UserVoOrders;
import com.xuan.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class demo {

    public static void main(String[] args) {
        Configuration configuration=new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user=new User();
        user.setName("121233");
       // session.save(user);
      //  session.save(new User(1,"Jack",125));
      //session.update(new User(1,"Jack",15));
       // session.saveOrUpdate(new User(1,"Jack",1225));   //不指定主键或主键为0 null 就是插入 指定了就是更新
       /* User user1 = (User) session.get("com.xuan.domain.User", 1);
        System.out.println(user1);
        User user2 = session.get(User.class, 1);
        System.out.println(user2);*/
      /*  Query from_user = session.createQuery("FROM User");//非主键的sql int值 实体类应用Integer
        List<User> list = from_user.list();
        for(User u:list)
        {
            System.out.println(u);
        }*/

        /*Query query = session.createQuery("update User  u set u.name='杀杀杀' where u.id=:id");
        query.setParameter("id",1);
        query.executeUpdate();*/

            session.createQuery("delete User where id=:id").setParameter("id",4).executeUpdate();


     /*   Query query = session.createQuery("select new com.xuan.domain.UserVoOrders(u.id,o.uid,u.age,u.name,o.phone) FROM "+User.class.getName()+" as u left join Orders  as o on u.id=o.uid where u.id > ? order by u.id desc ");
        query.setParameter(0,1);  //jdbc 下标是1开始 这里是0开始

       *//* Query query = session.createQuery("select new User (u.id ,u.name) FROM "+User.class.getName()+" as u  where u.id = :id order by u.id desc ");
        query.setString("id","1");*//*
        List<UserVoOrders> list = query.list();
        for(UserVoOrders u:list)
        {
            System.out.println(u);
        }*/
      /*  List<User> list = query.list();
        for(User u:list)
        {
            System.out.println(u);
        }*/

    /*   NativeQuery sqlQuery = session.createSQLQuery("select * from user where id=4").addEntity(User.class);

        List<User> list = sqlQuery.list();
        for(User u:list)
        {
            System.out.println(u);
        }*/

        transaction.commit();
        session.close();
    }
}

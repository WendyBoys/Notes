package com.xuan.Ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRules {

    //替换默认的轮询 使用随机  切记 该类不能和启动类同包 同子包 要新建包 不然会给全局的Ribbon都覆盖了
    @Bean
    public IRule iRule()
    {
        return new RandomRule();
    }
}

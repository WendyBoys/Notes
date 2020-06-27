package com.xuan.cloud.loadBalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LunXun implements LoadBalancer {

    @Resource
    private DiscoveryClient discoveryClient;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public int get()
    {
        int current;
        int next;
        do{
            current = atomicInteger.get();
            next = (current>=Integer.MAX_VALUE) ? 0 : current + 1;
        } while(!atomicInteger.compareAndSet(current,next));
        System.out.println("*****第"+current+"次访问");
        return next;
    }



    @Override
    public ServiceInstance serviceInstance(List<ServiceInstance> serviceInstanceList) {
        int index=get() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }
}

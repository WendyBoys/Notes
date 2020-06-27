package com.xuan.cloud.loadBalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;


public interface LoadBalancer {
    ServiceInstance serviceInstance(List<ServiceInstance> serviceInstanceList);
}

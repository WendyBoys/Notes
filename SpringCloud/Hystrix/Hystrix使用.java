Hystrix一般使用在客户端 但是服务端也可以 controller  service 实现方法 反正哪里都行 

因为请求慢 注意openfeign的ribbon connectionout readtimeout 时间
	
	
在服务端配置 启动类加@EnableCircuitBreaker

	@HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String timeout(int id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        log.info("8001service");
       // int a=10/0;
        return Thread.currentThread().getName()+"timeout*****"+id;
    }

    public String timeoutHandler(int id)//注意参数 返回值类型 要和上面一样 不然无法识别
    {
        return Thread.currentThread().getName()+"服务器繁忙，请稍后再试!";
    }
	
在客户端配置 启动类加@EnableHystrix

*** yml加了以下配置 会导致在客户端请求如果时间超过1s没返回直接进入兜底方法 即使 客户端允许超时时间没有被达到 很坑 就别加了
yml加
#客户端开启hystrix 要配置这个 服务端没feign 开个鸡毛
feign:
  hystrix:
    enabled: true
	
	
	
	@GetMapping(value = "/consumer/pay/hystrix/timeout")
    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2500")})
    public String timeout(int id) throws InterruptedException {
        log.info("timeout");
        return  paymentService.timeout(id);
    }

    public String timeoutHandler(int id)//注意参数 返回值类型 要和上面一样 不然无法识别
    {
        return Thread.currentThread().getName()+"客户端繁忙，请稍后再试!";
    }
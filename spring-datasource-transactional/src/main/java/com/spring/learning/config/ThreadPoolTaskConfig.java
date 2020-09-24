package com.spring.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author: yangyk
 * Date: 2020/9/24 14:51
 * Description: 线程池配置
 */
@Configuration
@EnableAsync
public class ThreadPoolTaskConfig {


	/**
	 * 核心线程数（默认线程数）
	 */
	private static final int SIZE_CORE_POOL = 5;
	/**
	 * 最大线程数
	 */
	private static final int SIZE_MAX_POOL = 20;
	/**
	 * 允许线程空闲时间（单位：默认为秒）
	 */
	private static final int ALIVE_TIME = 60;
	/**
	 * 缓冲队列大小
	 */
	private static final int QUEUE_CAPACITY = 100;
	/**
	 * 线程池名前缀
	 */
	private static final String THREAD_NAME_PREFIX = "Async-Service-";

	/**
	 * 默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
	 * 当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
	 * 当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
	 */
	@Bean("asyncServiceExecutor") // bean的名称，默认为首字母小写的方法名
	public ThreadPoolTaskExecutor asyncServiceExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(SIZE_CORE_POOL);
		executor.setMaxPoolSize(SIZE_MAX_POOL);
		executor.setQueueCapacity(QUEUE_CAPACITY);
		executor.setKeepAliveSeconds(ALIVE_TIME);
		executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
		// 线程池对拒绝任务的处理策略
		// CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		// 初始化
		executor.initialize();
		return executor;
	}


}

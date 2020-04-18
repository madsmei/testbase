//package com.zg.job.ejob;
//
//import com.cxytiandi.elasticjob.annotation.ElasticJobConf;
//import com.dangdang.ddframe.job.api.ShardingContext;
//import com.dangdang.ddframe.job.api.simple.SimpleJob;
//import com.enjoy.business.EnjoyBusiness;
//import io.shardingsphere.shardingjdbc.jdbc.core.ShardingContext;
//import org.springframework.beans.factory.annotation.Autowired;
//
//@ElasticJobConf(name = "EnjoySimpleJob",cron = "0/5 * * * * ?"
//		,shardingItemParameters = "0=beijing|shenzhen|tianjin,1=shanghai",shardingTotalCount = 2
//		,listener = "com.enjoy.handle.MessageElasticJobListener"
//		,jobExceptionHandler = "com.enjoy.handle.CustomJobExceptionHandler"
//)
//public class EnjoySimpleJob implements SimpleJob {
//
//	public void execute(ShardingContext context) {
//		System.out.println("EnjoySimpleJob,当前分片："+context.getShardingParameter());
//
//	}
//
//}
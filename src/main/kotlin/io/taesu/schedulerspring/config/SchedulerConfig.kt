package io.taesu.schedulerspring.config

import net.javacrumbs.shedlock.core.LockProvider
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import javax.sql.DataSource

/**
 * Created by itaesu on 2024. 7. 8..
 *
 * @author Lee Tae Su
 * @version scheduler-spring
 * @since scheduler-spring
 */
@EnableSchedulerLock(defaultLockAtLeastFor = "PT10S", defaultLockAtMostFor = "PT10M")
@EnableScheduling
@Configuration
class SchedulerConfig: SchedulingConfigurer {
    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        ThreadPoolTaskScheduler().apply {
            poolSize = 10
            setThreadNamePrefix("scheduler-")
            initialize()
            taskRegistrar.setTaskScheduler(this)
            setAwaitTerminationSeconds(300)
            setWaitForTasksToCompleteOnShutdown(true)
        }
    }

    @Bean
    fun lockProvider(dataSource: DataSource): LockProvider {
        return JdbcTemplateLockProvider.Configuration.builder()
            .withJdbcTemplate(JdbcTemplate(dataSource))
            .usingDbTime()
            .build()
            .let(::JdbcTemplateLockProvider)
    }
}

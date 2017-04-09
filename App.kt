package com.example

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import javax.sql.DataSource
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
@ServletComponentScan
open class KotlinApplication
{
	@Bean
	open fun init(repository: StudentRepository) = CommandLineRunner{
		repository.save(Student("vineet","jha"))
		repository.save(Student("Yoga", "nanda"))
		}
	}
 private val log = LoggerFactory.getLogger(KotlinApplication::class.java)


fun main(args: Array<String>) {
    SpringApplication.run(KotlinApplication::class.java, *args)
}

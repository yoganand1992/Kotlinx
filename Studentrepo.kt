package com.example

import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, String>
{
	fun findBylastNameStartsWithIgnoreCase(lastName: String): List<Student>
}

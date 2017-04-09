package com.example

import javax.persistence.Entity
import java.io.*
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Student{
	
	@Id
	@GeneratedValue
	var id: Long = 0
	var firstName: String? = null
	var lastName: String? = null
	var dob: String? = null
	var mailid: String? = null
	var sex: String? = null
	
	init{
		this.id = id
	}
	public constructor(firstName: String, lastName: String){
		this.firstName = firstName
		this.lastName = lastName
	}
	fun display(){
		println("Name" + " " + firstName + lastName)
	}
}

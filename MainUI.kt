package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils

import com.vaadin.annotations.Theme
import com.vaadin.data.util.BeanItemContainer
import com.vaadin.server.FontAwesome
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.spring.annotation.EnableVaadin
import com.vaadin.ui.Button
import com.vaadin.ui.Grid
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

@SpringUI
		@Theme("valo")
		class MainUI
@Autowired
		constructor(private val repo: StudentRepository, private val editor: StudentEditor): UI(){
	private val grid: Grid
	private val filter: TextField
	private val addNewBtn: Button
	init{
		this.grid = Grid()
		this.filter = TextField()
		this.addNewBtn = Button("New Student", FontAwesome.PLUS)
		}
	override protected fun init(request: VaadinRequest){
		val actions = HorizontalLayout(filter, addNewBtn)
		val mainLayout = VerticalLayout(actions, grid, editor)
		setContent(mainLayout)
		actions.setSpacing(true)
		mainLayout.setMargin(true)
		mainLayout.setSpacing(true)
		
		grid.setColumns("id","firstName","lastName")
		filter.setInputPrompt("Filter by last name")
		
		grid.addSelectionListener({ e ->
		if(e.getSelected().isEmpty())
	{
		editor.setVisible(false)
	}
		else{
			
		}
		})
		
	}
	private fun listStudents(text: String?){
		if (StringUtils.isEmpty(text)){
			grid.setContainerDataSource(
			BeanItemContainer(Student::class.java, repo.findAll()))
		} else {
			grid.setContainerDataSource(BeanItemContainer(Student::class.java, repo.findBylastNameStartsWithIgnoreCase("")))
		}
	}
}

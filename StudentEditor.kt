package com.example

import org.springframework.beans.factory.annotation.Autowired
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.event.ShortcutAction
import com.vaadin.server.FontAwesome
import com.vaadin.spring.annotation.SpringComponent
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.Button
import com.vaadin.ui.CssLayout
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

fun main(args: Array<String>) {
	
}
@SpringComponent
@UIScope

	class StudentEditor
@Autowired
		constructor(private val repository: StudentRepository): VerticalLayout()
{
	private var student: Student? = null
	internal var firstName = TextField("First Name")
	internal var lastName = TextField("Last Name")
	
	internal var save = Button("Save", FontAwesome.SAVE)
	internal var cancel = Button("cancel")
	internal var delete = Button("Delete", FontAwesome.TRASH_O)
    internal var actions = CssLayout(save, cancel, delete)
	
	init{
		addComponents(firstName, lastName, actions)
		setSpacing(true)
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP)
		save.setStyleName(ValoTheme.BUTTON_PRIMARY)
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER)
		save.addClickListener({e -> repository.save(student)})
		delete.addClickListener({e -> repository.delete(student)})
		setVisible(false)
		}
	interface ChangeHandler {
		fun onChange()
	}
	
	fun editStudent(c:Student){
		var persisted = getId()!= null
		if(persisted){
			student = repository.findOne(c.toString())
		}
		else{
			student = c
		}
		BeanFieldGroup.bindFieldsUnbuffered(student, this)
		setVisible(true)
		save.focus()
		firstName.selectAll()
	}
	fun setChangeHandler(h: ChangeHandler){
		save.addClickListener({e -> h.onChange()})
		delete.addClickListener({e -> h.onChange()})
		
	}
}

package isen.service;

import isen.db.entities.Contact;
import isen.quiz.model.Answer;
import isen.quiz.model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactService {

	private ObservableList<Contact> contacts;

	private ContactService() {
		contacts = FXCollections.observableArrayList();
		contacts.add(new Contact(0, "", "", "", "", "", ""));
	
	}

	public static ObservableList<Question> getQuestions() {
		return QuestionServiceHolder.INSTANCE.questions;
	}

	public static void addQuestion(Question question) {
		QuestionServiceHolder.INSTANCE.questions.add(question);
	}

	private static class QuestionServiceHolder {
		private static final ContactService INSTANCE = new ContactService();
	}

}

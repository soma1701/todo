package com.bridgelabz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.dao.LabelDAO;
import com.bridgelabz.model.Labels;
import com.bridgelabz.model.User;

public interface LabelService {

	void saveLabel(Labels labels);

	boolean deleteLabelById(int id);

	List<Labels> getLabels(User user);

	Labels getLabelById(int labelId);
	
	Labels getLabelByName(String labelName);

	boolean editLabel(Labels label);

}

package com.bridgelabz.dao;

import java.util.List;

import com.bridgelabz.model.Labels;
import com.bridgelabz.model.User;

public interface LabelDAO {

	void saveLabel(Labels labels);

	void deleteById(int id);

	List<Labels> getLabels(User user);

	Labels getLabelById(int labelId);

	boolean editLabel(Labels label);

	Labels getLabelByName(String labelName);

}

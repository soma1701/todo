package com.bridgelabz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.dao.LabelDAO;
import com.bridgelabz.dao.NotesDAO;
import com.bridgelabz.model.Labels;
import com.bridgelabz.model.User;

public class LabelServiceImpl implements LabelService{

	@Autowired
	LabelDAO labelDao;
	
	@Override
	public void saveLabel(Labels labels) {
		labelDao.saveLabel(labels);
	}

	@Override
	public boolean deleteLabelById(int id) {
		labelDao.deleteById(id);
		return true;
	}

	@Override
	public List<Labels> getLabels(User user) {
		return labelDao.getLabels(user);
	}

	@Override
	public Labels getLabelById(int labelId) {
		return labelDao.getLabelById(labelId);
	}

	@Override
	public boolean editLabel(Labels label) {
		labelDao.editLabel(label);
		return true;
	}

	@Override
	public Labels getLabelByName(String labelName) {
		return labelDao.getLabelByName(labelName);
	}

}

package com.bridgelabz.controller;

import java.util.HashSet;

public class Test {
public static void main(String[] args) {
	HashSet<Test> hSet = new HashSet<>();
	Test obj1 = new Test();
	Test obj2 = new Test();
	obj1 = obj2;
	hSet.add(obj1);
	hSet.add(obj2);
	System.out.println(hSet.toString());
}
}

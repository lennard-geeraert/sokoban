module SOKOBAN 
{
	exports persistentie;
	exports cui;
	exports gui;
	exports main;
	exports domein;

	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens gui to javafx.graphics, javafx.fxml;
	opens main to javafx.graphics, javafx.fxml;
}
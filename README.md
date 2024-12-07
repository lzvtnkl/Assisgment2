# Assisgment2
This repository is created for the purpose of presenting my homework for the course "Programming 2" at FHNW
This program consists of 4 files.

The class main invokes CSVGenerator class, Statistic class and AnalyzerService class. Also the main class displays the results of directory traversing, counts the number of files, the number of Java files, the average file size in words and bytes. 

Each of the classes calls the statistic class, in which we create a class that is the basis of the vector. There we make a blueprint of the class, create constructor and create getters. 

The CSVGenerator file generates a csv file in which all data from the statistic class is written.

The basis of our program is the analyzerService class. All mathematical calculations occur in it: after directory is analyzed this class calculates the cosine of a matrix, comparing matrices for file similarity.

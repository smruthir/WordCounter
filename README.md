WordCounter
===========

Word Count rest api 


Step1: Create a folder or place the csv file with few testfiles in any location in the computer
Step2: Configure the CSV path in the file MultipleFileReader.java [variable=csvFile]-sample is FileNameList.csv
Step3: In the CSV file provide the files which need to be read(Should be absoulte path to the files COMMA SEPERATED -sample is Test.txt,Text1.txt,Test2.txt)<br />
Step4: Import the project into the eclipse workspace
Step5: Build the project and see there is no compilation issue
Step6: Update the csv filepath in the MultipleFileReader.java file
Step7: Start the tomcat server<br />
Step8: Open Firefox browser and enter the below links

Version: http://localhost:8080/WordCounter/api/v1/wordcounter/version</a>
WordCounter CaseInsenstive search: http://localhost:8080/WordCounter/api/v1/wordcounter?query=TEST</a>
WordCounter CaseInsenstive search: http://localhost:8080/WordCounter/api/v1/wordcounter?query=test</a>
Negative testcase where queryparam is wrong: http://localhost:8080/WordCounter/api/v1/wordcounter?wrongquery=test</a>

WordCounter
===========

Word Count rest api 


Step1: Create a folder or place the csv file with few testfiles in any location in the computer<br />
Step2: Configure the CSV path in the file MultipleFileReader.java [variable=csvFile]-sample is FileNameList.csv<br />
Step3: In the CSV file provide the files which need to be read(Should be absoulte path to the files COMMA SEPERATED -sample is Test.txt,Text1.txt,Test2.txt)<br />
Step4: Import the project into the eclipse workspace<br />
Step5: Build the project and see there is no compilation issue<br />
Step6: Update the csv filepath in the MultipleFileReader.java file<br />
Step7: Start the tomcat server<br />
Step8: Open Firefox browser and enter the below links<br />

<br />Version: <a href="http://localhost:8080/WordCounter/api/v1/wordcounter/version">http://localhost:8080/WordCounter/api/v1/wordcounter/version</a>
<br />WordCounter CaseInsenstive search: <a href="http://localhost:8080/WordCounter/api/v1/wordcounter?query=TEST">http://localhost:8080/WordCounter/api/v1/wordcounter?query=TEST</a>
<br />WordCounter CaseInsenstive search:<a href="http://localhost:8080/WordCounter/api/v1/wordcounter?query=test">http://localhost:8080/WordCounter/api/v1/wordcounter?query=test</a>
<br />Negative testcase where queryparam is wrong: 
<a href="http://localhost:8080/WordCounter/api/v1/wordcounter?wrongquery=test">http://localhost:8080/WordCounter/api/v1/wordcounter?wrongquery=test</a>

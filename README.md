# Character Frequency Counter

### Description
Motivation behind this small project is to experiment with Java 21's latest features, including virtual threads and string templates.

This application concurrently scans files within a specified directory path, reading all .txt files in parallel. Each file is processed in its own virtual thread, allowing for efficient computation of character frequency. 

Finally, the results from all files are combined into a unified output.
### How to run application?
```
mvn clean install
java --enable-preview -jar target/CharacterFrequencyCounter-1.0-SNAPSHOT.jar <directory_path>
```
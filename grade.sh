LINUX_CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'
WINDOWS_CPATH='.;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf testing
rm -rf results.txt

git clone $1 student-submission
echo "Finished cloning"

cd student-submission
if [[ -f ListExamples.java ]]
then
    echo "File found"
else
    echo "File not found, try changing the name of the file to ListExamples"
    exit 1
fi

cd ..
mkdir testing
cp student-submission/ListExamples.java TestListExamples.java testing
cd testing
mkdir lib
cp ../lib/hamcrest-core-1.3.jar ../lib/junit-4.13.2.jar lib

if [[ "$OSTYPE" == "linux-gnu"* || "$OSTYPE" == "darwin"* ]]
then
    javac -cp $LINUX_CPATH *.java
else
    javac -cp "$WINDOWS_CPATH" *.java
fi

if [[ $? -ne 0 ]]
then
    echo "Failed compilation, please check your file for errors!"
    exit 1
else
    echo "Compilation success!"
fi

if [[ "$OSTYPE" == "linux-gnu"* || "$OSTYPE" == "darwin"* ]]
then
    java -cp $LINUX_CPATH org.junit.runner.JUnitCore TestListExamples > results.txt
else
    java -cp "$WINDOWS_CPATH" org.junit.runner.JUnitCore TestListExamples > results.txt
fi

tail -3 results.txt > check.txt
grep -q "FAILURES" check.txt
if [[ $? -eq 0 ]]
then
    testsFailed=`grep -w "Tests" check.txt`
    lastCharacter=${testsFailed: -1}
    echo "You only passed ${lastCharacter} out of 6 tests :("
else
    echo "You passed all the tests! Congrats!"
fi
CPATH='.;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf testing
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

ls
javac -cp "$CPATH" *.java
if [[ $? -ne 0 ]]
then
    echo "Failed compilation, please check your file for errors!"
    exit 1
else
    echo "Compilation success!"
fi

java -cp "$CPATH" org.junit.runner.JUnitCore TestListExamples > results.txt
PASSCOUNT=`grep -c "OK" results.txt`

if [[ $PASSCOUNT -eq 1 ]]
then
    echo "Congrats, you passed! A for you!"
else 
    echo "You are a complete failure. You get an F for failing all these tests."
fi
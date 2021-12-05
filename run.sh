#!/bin/sh

 javac -cp junit-4.13.2.jar:hamcrest-core-1.3.jar:.  --module-path . \
    --add-modules javafx.controls  -Xlint \
   Graph.java GraphTest.java     

#java -cp junit-4.13.2.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore Graph_GFA_Test

java -cp junit-4.13.2.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore GraphTest
#java -cp junit-4.13.2.jar:hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore TownGraphManager_GFA_Test

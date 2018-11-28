#Calcolatore termodinamico

## Execute demo
mvn compile
linux
  ./demo.sh
windows
 execdemo.bat


##Execute

Java version 1.8 (tested)

mvn package

###Execute from target dir
linux
  ./execute.sh
windows
 exectarget.bat

###Exceute wherewere
Copy calctermodin-2-jar-with-dependencies.jar (can be renamed) and execute as jar
  java -jar calctermodin-2-jar-with-dependencies.jar
OR
copy calctermodin-2.jar in a directory with lib/dependecixx.jar and execute
  java -jar calctermodin-2.jar

##Pannello Diagramma di Moody

Plots the Diagram of Moody .
Insert diameter and relative pipe roughness
OR
insert directly friction factor and tha Moodu Dyagram is calculated and plotted


##Pannello Combustibile
Calcluate chemical properties of gas as EN 13384-1

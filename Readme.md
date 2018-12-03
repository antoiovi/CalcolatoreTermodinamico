#Calcolatore termodinamico

##Mandatories dependencies

https://github.com/antoiovi/uniciglib (2.1)

https://github.com/antoiovi/utilities

Both must be downloaded and excete :
  mvn install
to have in local maven repository the dependency.

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

###Exceute everywhere
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
Calcluate chemical properties product of combustion as specified by UNI EN 13384-1

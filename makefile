all:  doc cls test run jar_executable jar



cls:
		@echo "\n Compilation des classes"
		javac -sourcepath src src/plateau/*.java -d classes
		javac -sourcepath src src/plateau/gamecharacter/*.java -d classes
		javac -sourcepath src src/plateau/gamecharacter/zombie/*.java -d classes
		javac -sourcepath src src/plateau/gamecharacter/exception/*.java -d classes
		javac -sourcepath src src/plateau/action/*.java -d classes
		javac -sourcepath src src/plateau/equipment/*.java -d classes
		javac -sourcepath src src/plateau/equipment/tool/*.java -d classes
		javac -sourcepath src src/plateau/equipment/weapon/*.java -d classes
		javac -sourcepath src src/plateau/role/*.java -d classes
		javac -sourcepath src src/plateau/room/*.java -d classes
		javac -sourcepath src src/plateau/street/*.java -d classes
		


run:
		@echo "\n Lancement du programme..."
		java -classpath classes plateau.Livrable4 10 14 4
		@echo "\n Execution terminee"



doc:
		@echo "\n Génération de la doc"
		javadoc -sourcepath src -subpackages plateau -d docs

test:
		@echo "\n Test du programme"
		javac -classpath junit-console.jar:classes test/plateau/*.java
		java -jar junit-console.jar -classpath test:classes -scan-classpath


jar_executable:
		jar cvfe BoardMain.jar plateau.BoardMain -C classes city


jar:	
		java -jar BoardMain.jar

		
clean:
		@echo "\n Cleaning \n"
		rm -rf all



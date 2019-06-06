@echo off
:start
cmd /C mvn package exec:java -Dexec.args="152" 
goto start
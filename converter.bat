@echo off
cd /d "%~dp0"
cls

title Converter
cd src

if exist plugin.yml ( call :AntToMaven ) else ( call :MavenToAnt )

goto exit

:AntToMaven

title Converting Ant -^> Maven
echo Converting Ant -^> Maven

echo Make DIR: src\main
mkdir .\main\ >nul

echo Make DIR: src\main\java
mkdir .\main\java\ >nul

echo Make DIR: src\main\resources
mkdir .\main\resources\  >nul

echo Move: src\com -^> src\main\java\com
move .\com .\main\java\  >nul

for /f %%i in (' dir /b ') do if "%%i" neq "main" (
    echo Move: src\%%i -^> src\main\resources\%%i
    move .\%%i .\main\resources\ >nul
)

title Converted
echo Converted

timeout 3 >nul

goto exit



:MavenToAnt

title Converting Maven -^> Ant
echo Converting Maven -^> Ant

echo Move: src\main\java\com -^> src\com
move .\main\java\com .\ >nul

for /f %%i in (' dir /b .\main\resources\ ') do (
    echo Move: src\main\resources\%%i -^> src\%%i
    move .\main\resources\%%i .\ >nul
)

echo Remove DIR: src\main\java
rmdir .\main\java\ >nul

echo Remove DIR: src\main\resources
rmdir .\main\resources\ >nul

echo Remove DIR: src\main
rmdir .\main\ >nul

title Converted
echo Converted

timeout 3 >nul

goto exit



:exit
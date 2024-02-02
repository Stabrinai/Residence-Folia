#!/bin/bash

main(){
clear

title Converter
cd src

if [ -f "plugin.yml" ]; then
    AntToMaven
else
    MavenToAnt
fi

}

AntToMaven(){

title Converting Ant -\> Maven
echo Converting Ant -\> Maven

echo Make DIR: src/main
mkdir ./main/

echo Make DIR: src/main/java
mkdir ./main/java/

echo Make DIR: src/main/resources
mkdir ./main/resources/ 

echo Move: src/com -\> src/main/java/com
mv ./com ./main/java/ 

read -ra arr <<< $( dir )
for i in ${arr[@]}; do
    if [ ! "$i" == "main" ]; then
        echo Move: src/$i -\> src/main/resources/$i
        mv ./$i ./main/resources/$i
    fi
done

title Converted
echo Converted

sleep 3

}



MavenToAnt(){

title Converting Maven -\> Ant
echo Converting Maven -\> Ant

echo Move: src/main/java/com -\> src/com
mv ./main/java/com ./

cd ./main/resources/
read -ra arr <<< $( dir )
cd ../..
for i in ${arr[@]}; do
    echo Move: src/main/resources/$i -\> src/$i
    mv ./main/resources/$i ./$i
done

echo Remove DIR: src/main/java
rmdir ./main/java/

echo Remove DIR: src/main/resources
rmdir ./main/resources/

echo Remove DIR: src/main
rmdir ./main/

title Converted
echo Converted

sleep 3

}

title() {
    echo -n -e "\033]0;$1\007"
}

main
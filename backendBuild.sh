mkdir -p backendBuild
rm -rf ./backendBuild/*
cd ./backend || exit
mvn clean package
cp ./target/technical-business-essentials-1.0-SNAPSHOT.jar ../backendBuild

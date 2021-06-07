rm -rf ./build/backend/*
cd ./backend || exit
mvn clean package
cp ./target/technical-business-essentials-1.0-SNAPSHOT.jar ../build/backend

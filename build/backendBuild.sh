mkdir -p ./backend
rm -rf ./backend/*
cd ../
mvn clean package
cd ./backend || exit
cp ./target/technical-business-essentials-1.0-SNAPSHOT.jar ../build/backend

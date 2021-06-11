# Starts from root directory.
mkdir -p ./build/backend
rm -rf ./build/backend/*
mvn clean package
cd ./backend || exit
cp ./target/technical-business-essentials-1.0-SNAPSHOT.jar ../build/backend

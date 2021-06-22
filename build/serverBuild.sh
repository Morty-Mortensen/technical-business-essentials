# Starts from root directory.
mkdir -p ./build/server
rm -rf ./build/server/*
cd ./backend || exit
gradle clean shadowJar
cp ./build/libs/server-all.jar ../build/server

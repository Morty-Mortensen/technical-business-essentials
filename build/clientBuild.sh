# Starts from root directory.
mkdir -p ./build/client
rm -rf ./build/client/*
rm -rf ./client/dist
cd ./client || exit
npm install
npm run build
cd ./dist/client || exit
zip -r client.zip . -x ./dist/client/assets/**\*
mv client.zip ../../../build/client
cd ../../../build/client || exit
unzip client.zip
rm -f client.zip

# Pictures will need to be deleted manually.

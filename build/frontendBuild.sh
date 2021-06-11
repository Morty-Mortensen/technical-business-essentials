# Starts from root directory.
mkdir -p ./build/frontend
rm -rf ./build/frontend/*
rm -rf ./frontend/dist
cd ./frontend || exit
npm install
npm run build
cd ./dist/frontend || exit
zip -r frontend.zip . -x ./dist/frontend/assets/**\*
mv frontend.zip ../../../build/frontend
cd ../../../build/frontend || exit
unzip frontend.zip
rm -f frontend.zip

# Pictures will need to be deleted manually.

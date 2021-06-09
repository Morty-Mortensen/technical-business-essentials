mkdir -p ./build/frontend
rm -rf ./build/frontend/*
rm -rf ./frontend/dist
cd ./frontend || exit
npm install
npm run build
zip -r -j frontend.zip ./dist/frontend/. -x "*.DS_Store"
mv frontend.zip ../build/frontend
cd ../build/frontend || exit
unzip frontend.zip
rm -f frontend.zip

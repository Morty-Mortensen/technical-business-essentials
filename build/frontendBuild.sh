mkdir -p ./frontend
rm -rf ./frontend/*
echo "---Entering Frontend---"
pwd
cd ./frontend || exit
echo "---In Frontend---"
pwd
rm -rf ./dist
npm install
npm run build
zip -r -j frontend.zip ./dist/frontend/. -x "*.DS_Store"
mv frontend.zip ../build/frontend
cd ../build/frontend || exit
unzip frontend.zip
rm -f frontend.zip

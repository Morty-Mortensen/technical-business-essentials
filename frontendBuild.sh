mkdir -p frontendBuild
rm -rf ./frontendBuild/*
cd frontend || exit
rm -rf ./dist
npm install
npm run build
zip -r -j frontend.zip ./dist/frontend/. -x "*.DS_Store"
mv frontend.zip ../frontendBuild

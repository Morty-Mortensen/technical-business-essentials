mkdir -p frontendBuild
rm -rf ./frontendBuild/*
cd frontend || exit
rm -rf ./dist
npm install
npm run build
zip -r -j frontend.zip ./dist/frontend/. -x "*.DS_Store"
mv frontend.zip ../frontendBuild
cd ../frontendBuild || exit
unzip frontend.zip
for file in ./main.*
do
   mv "$file" "main.js"
done
for file in ./polyfills.*
do
   mv "$file" "polyfills.js"
done
for file in ./runtime.*
do
   mv "$file" "runtime.js"
done
for file in ./styles.*
do
   mv "$file" "styles.css"
done

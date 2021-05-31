# Frontend build
rm -rf ./build/*
#cd frontend || exit
#rm -rf ./dist
#npm run build
#zip -r -j frontend.zip ./dist/frontend/. -x "*.DS_Store"
#mv frontend.zip ../build
## Transition
cd ./backend || exit #REMOVE LATER
#cd ../backend || exit
# Backend build
mvn clean package
cp ./target/technical-business-essentials-1.0-SNAPSHOT.jar ../build






#yarn workspace @oare/types build
#yarn workspace @oare/oare build
#yarn workspace @oare/frontend build
#yarn workspace @oare/backend build
#cd packages/backend
#rm -rf dist
#cp -r ../frontend/dist .
#mkdir @oare
#mkdir @oare/oare
#cp ../oare/package.json ./@oare/oare
#cp -r ../oare/build ./@oare/oare

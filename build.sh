# Build Directory
mkdir -p build

# Client
mkdir -p ./build/client
rm -rf ./build/client/*
rm -rf ./client/dist
cd ./client || exit
npm install
npm run build
cd ./dist/client || exit
zip -r client.zip . -x ./dist/client/assets/**\*
mv client.zip ../../../build/client
cd ../../../build/client/ || exit
unzip client.zip
rm -f client.zip
cd ../../ || exit

# Server
mkdir -p ./build/server
rm -rf ./build/server/*
cd ./server || exit
mvn clean package
cd ./target || exit
mv technical-0.0.1-SNAPSHOT.jar ../../build/server
cd ../../ || exit

# Update client side on server.
scp -i "technicalbusinessessentials.pem" -r ./build/client/* ec2-user@ec2-54-219-136-197.us-west-1.compute.amazonaws.com:/var/www/html

# Update server side on server.
scp -i "technicalbusinessessentials.pem" ./build/server/technical-0.0.1-SNAPSHOT.jar ec2-user@ec2-54-219-136-197.us-west-1.compute.amazonaws.com:~/

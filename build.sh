mvn package docker:build

docker-compose up -d


sleep 10

docker cp src/main/resources/data.json doodle_mongodb:/tmp/data.json


docker exec -it doodle_mongodb mongoimport -d test -c doodle --file /tmp/data.json --jsonArray
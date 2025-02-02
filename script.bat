cd eclipse-workspace
docker rm -f $(docker ps -aq)
docker-compose up -d
curl http://localhost:8080/
curl http://localhost:8161/
curl http://localhost:3000/
start http://localhost:8080/index.html
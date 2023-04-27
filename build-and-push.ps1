#    .\build-and-push.ps1
docker build -t word-cloud-core:latest .
docker tag word-cloud-core estken/word-cloud-core
docker push estken/word-cloud-core
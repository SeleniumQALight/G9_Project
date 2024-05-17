docker-compose -f grid.yaml up --scale firefox=1 --scale chrome=1 -d

docker-compose -f tests.yaml up


docker-compose -f tests.yaml down

docker-compose -f grid.yaml down
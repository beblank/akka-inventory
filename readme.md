
## Usage

[Install SBT](https://www.scala-sbt.org/)

### Start up an instance of Mysql db:

```sh
brew install mysql
mysql.server start
mysql -u root

CREATE DATABASE inventorydb;
```


### Start services with sbt:

```sh
$ sbt run
```


### Create an Item

Using CURL:

```sh
curl -X "POST" "http://localhost:8080/items" \
       -H "Accept: application/json" \
       -H "Content-Type: application/json" \
       -d $'{
  "sku":"SSI-D00864661-MM-MAR",
  "name": "Deklia Plain Casual Blouse (M,Maroon)",
  "qty": 12
  }'
```

### Get All items


```sh
curl -X "GET" "http://localhost:8080/items"
```

### Get A Item

```sh
curl -X "GET" "http://localhost:8080/items/1"
```

### Update Item



```sh
curl -X "PUT" "http://localhost:8080/items/1" \
     -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -d $'{
  "sku":"SSI-D00864661-MM-MAR",
  "name": "Deklia Plain Casual Blouse (M,Maroon)",
  "qty": 16
  }'
```


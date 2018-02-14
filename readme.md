
## Usage

### Start up an instance of Mysql db:
[source, plain]
----
brew install mysql
mysql.server start
mysql -u root

CREATE DATABASE inventorydb;
----


### Start services with sbt:

[source, java]
----
$ sbt run
----


### Create an Item

Using CURL:

[source, java]
----
curl -X "POST" "http://localhost:8080/items" \
       -H "Accept: application/json" \
       -H "Content-Type: application/json" \
       -d $'{
  "sku":"SSI-D00864661-MM-MAR",
  "name": "Deklia Plain Casual Blouse (M,Maroon)",
  "qty": 12
  }'
----

### Get All items


[source, java]
----
curl -X "GET" "http://localhost:8080/items"
----

### Get A Item

[source, java]
----
curl -X "GET" "http://localhost:8080/items/1"
----

### Update Item


[source, java]
----
curl -X "PUT" "http://localhost:8080/items/1" \
     -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -d $'{
  "sku":"SSI-D00864661-MM-MAR",
  "name": "Deklia Plain Casual Blouse (M,Maroon)",
  "qty": 16
  }

----


# Team 2: E-Commerce Site

## Project Description
The E-Commerce site allows users to register as either a customer or a store. Customers can browse all items for sale on the site or filter their search down to a specific store or price. They may also add or remove items from their shopping cart and wishlist. Stores may create items for sale. 

## Technologies Used
* Amazon Keyspaces
* Spring Boot - version 2.5.0
* Spring Webflux - version 2.5.0
* Spring Data Cassandra - version 3.2.1
* Intuit Karate - version 1.1.0


## Features
* Create items for sale as a Store
* Add items to your shopping cart or wishlist as a Customer
* Browse items by store or filter items by price

To-Do List:
* Complete order creation/checkout and currency feature
* Automatically add created items to the store's inventory list and remove them when they're sold
* Complete unit tests for the service layer

## Getting Started
Use `cd` to go to the file you want the project in.

Clone the repository using:
`git clone https://github.com/210712-Richard/team2.p2.git`

In "Run Configurations", select or create the driver that runs this project. Go to it's environment variables tab. Add an AWS_USER variable with the value of your keyspace credentials username. Add an AWS_PASS variable with the value of your keyspace credentials password.
*If you're using your own keyspace account, make sure your keyspace is named p2.*

Create a Truststore for AWS Keyspaces

### BASH Terminal TrustStore (these commands will not work in powershell)
1. Open a BASH terminal inside of src/main/resources folder.
2. `curl https://certs.secureserver.net/repository/sf-class2-root.crt -O`
3. `openssl x509 -outform der -in sf-class2-root.crt -out temp_file.der`
4. `keytool -import -alias cassandra -keystore cassandra_truststore.jks -file temp_file.der`
5. Use the password: `p4ssw0rd`
6. Say yes when prompted

## Usage

### Registering as a Customer
1. POST to http://localhost:8080/users/{username}
2. In the body of the request have `{ "firstName" : "[first name]", "lastName" : "[last name]", "email" : "[email]","address" : "[address]","currency" : "[money amount]" }`

### Registering as a Store
1. POST to http://localhost:8080/stores/{store name}
2. In the body of the request have `{ "owner" : "[owner name]", "currency" : "[money amount] }`

### Logging in as a Customer
1. POST to http://localhost:8080/stores
2. In the body of the request have `{ "name" : "[store name]"}`

### Logging in as a Store
1. POST to http://localhost:8080/users
2. In the body of the request have `{ "name" : "[username]"}`

### Logging out as a Customer
1. DELETE from http://localhost:8080/users

### Logging out as a Store
1. DELETE from http://localhost:8080/stores

### View all Items for Sale
1. Login as a Customer
2. GET from http://localhost:8080/items/{username}

### View Items from a specific Store
1. GET from http://localhost:8080/stores/{store name}/items

### Filter Items by Price
1. Login as a Customer
2. GET from http://localhost:8080/items/{username}/price
3. Create a header named `price` and set the value as the top price you're willing to view
    * *ex. if you want to see items at or below $40 then fill `price` with 40*

### Create an Item
1. Login as a Store
2. POST to http://localhost:8080/stores/{store name}/items
3. In the body of the request have `{"name": "[item name]", "storename": "[store name]", "price": [item price], category": "[item category]" }`
    * Item categories available are: APPAREL, TECH, TOYS, PETS, HOUSEHOLD

### View Cart
1. Login as a Customer
2. GET from http://localhost:8080/users/{username}/shoppingCart

### View Wishlist
1. Login as a Customer
2. GET from http://localhost:8080/users/{username}/wishlist

### Add Item to Cart
1. Login as a Customer
2. POST to http://localhost:8080/users/{username}/shoppingCart
3. In the body of the request have `{"uuid" : "[item uuid]"}`

### Add Item to Wishlist
1. Login as a Customer
2. POST to http://localhost:8080/users/{username}/wishlist
3. In the body of the request have `{"uuid" : "[item uuid]"}`

### Remove Item from Cart
1. Login as a Customer
2. POST to http://localhost:8080/users/{username}/shoppingcart/garbage
3. In the body of the request have `{"uuid" : "[item uuid]"}`

### Remove Item from Wishlist
1. Login as a Customer
2. POST to http://localhost:8080/users/{username}/wishlist/garbage
3. In the body of the request have `{"uuid" : "[item uuid]"}`


## Contributors
* Quentin Hardwick
* Khine Thet
* Ivan Gastelum
* Alby Pawlisch

## License
This project uses the following license: <license_name>.

# Tax Calculator
Test project for tax calculation for Odealim

# To Launch this program
Simply launch "mvn:spring-boot:run"

# Methods to use the program
A few methods in this API will help you create, get and delete products. You will also be able to calculate the taxed price of a product.

the root link to the api is http://localhost:8080
since only the products have been set up (and it's the only thing that interests us), the full link to the Product Controller is: http://localhost:8080/api/products

## Create a product ##

To create a product, you will want to send a POST http request to /api/products with the following body:
{
  "name": "Product Name",
  "price": 100,
  "country": "FRANCE"
}

for "country", only "US", "CANADA" and "FRANCE" are allowed. The rest will not be valid and the request will fail.

## Get all products ##
You can get all the products by sending a GET http request to /api/products.

## Get Product by Id ##
You can get all the products by sending a GET http request to /api/products/{id} with "id" the id of the product.

Example: http://localhost:8080/api/products/1

## Delete Product by Id ##
You can remove a product by sending a DELETE http request to /api/products/{id}.

## Calculate Product Price with Tax ##
You can calculate the product price with the tax included by sending a GET http request to the following endpoint : /api/products/{id}/tax

Example: http://localhost:8080/api/products/1/tax


# Thank you !
# Backend dev technical test SOLUTION

The solution presented is a springboot application that exposes a reactive REST API on port 5000

Before continuing, make sure you have set up initial repository
https://github.com/dalogax/backendDevTest/blob/main/readme.md?plain=1

# How to run the application

Clone the repository
Navigate to the project directory
Run the following command to build and run the application:

Build docker image

```
docker build -t similar-products .
```

Run the application

```
docker run -p 5000:5000 -e EXTERNAL_SIMILAR_PRODUCTS_BASE_URL=http://host.docker.internal:3001 similar-products
```

Curl test

```
curl -X GET "http://localhost:5000/product/1/similar" \
-H "Accept: application/json"
```

Notes:

- This API and DTOs are generated automatically using OpenAPI Generator. It can be regenerated
  using the following command:

```
mvn clean generate-sources
```

- Maven is required
- SwaggerUI is not supported by springboot4 yet. The API definition can be checked in

```
http://localhost:5000/v3/api-docs
```
# Backend dev technical test SOLUTION

The solution presented is a springboot application that exposes a reactive REST API on port 5000

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
docker run -p 5000:5000 similar-products
```

Curl test

```
curl -X GET "http://localhost:5000/product/1/similar" \
-H "Accept: application/json"
```

If you want to use Docker's internal network

```
docker run -p 5000:5000 -e EXTERNAL_SIMILAR_PRODUCTS_BASE_URL=http://host.docker.internal:3001 similar-products
```
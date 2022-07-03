# REST Call from jBPM / RHPAM

The goal of this project is to show how to call a Rest Service from jBPM process.

This example provides two options:

- Default REST WorkItemHandler
- A custom WIH to perform the Rest call through the popular [Camel Framework](https://camel.apache.org/)

Learn more about the project details in the related module documents:

- [Knowledge Jar and Process logic](rest-wih-kjar/readme.md)
- [Springboot supporting service](rest-wih-service/readme.md)
- [Camel WorkItemHandler](camel-wih/readme.md)

## Run the Rest Mock server

This mock server expose a simple order fulfilment API: 

```sh
podman run --rm -it -v ./rest-mock-server:/root/apis:Z -p 4010:4010 stoplight/prism  mock -h 0.0.0.0 /root/apis/order-api.yaml
```

## Run the SpringBoot service

```sh
cd rest-wih-service
./launch.sh clean install
```

## Test the processes

The following command trigger the rest process, replace `rest_call` with `camel_rest_call` to trigger the process with the _Camel WIH_.

```sh
curl -u controllerUser:controllerUser1234\; --request POST \
  --url http://localhost:8090/rest/server/containers/rest-wih-kjar/processes/rest_call/instances \
  --header 'accept: application/json' \
  --header 'content-type: application/json' \
  --data '{"order" : {"com.redhat.demo.Order" : {"item" : "phone","quantity" : 4,"price" : 120.0}}}'
```
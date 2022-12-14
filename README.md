# FEIGN TEST CLOUD

[![Join the chat at https://gitter.im/OpenFeign/feign](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/OpenFeign/feign?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![CircleCI](https://circleci.com/gh/OpenFeign/feign/tree/master.svg?style=svg)](https://circleci.com/gh/OpenFeign/feign/tree/master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.openfeign/feign-core/badge.png)](https://search.maven.org/artifact/io.github.openfeign/feign-core/)

## Feature overview

This is a map with current key features provided by feign:

![MindMap overview](https://github.com/raulrobinson/feign-test-cloud/blob/master/img/feign_test.jpg)

shopping-service - controller/InvoiceRest.java

```java
    @GetMapping("/customers/{id}")
    public String getCustomer(@PathVariable("id") Long id) {
        return invoiceService.getCustomer(id);
    }
```

shopping-service - client/CustomerClient.java

```java
    @FeignClient(name = "customer", url = CUSTOMER_URL, fallback = CustomerHystrixFallbackFactory.class)
    public interface CustomerClient {
        @GetMapping(value = "/{id}")
        public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
    }
```

service-customer - controller/CustomerController.java

```java
    /**
     * GET CUSTOMER BY ID.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(
            @PathVariable("id") long id
    ){
        log.info("Fetching Customer with ID {}", id);
        Customer customer = customerService.getCustomer(id);
        if (null == customer){
            log.error("Customer with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    
        return ResponseEntity.ok(customer);
    }

```

### Collection Postman.

```json

{
	"info": {
		"_postman_id": "4e9548a5-c353-4b88-83cb-fb37abf617bf",
		"name": "FEIGN-CLOUD",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "CUSTOMERS ALL",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8051/customers/all",
					"host": [
						"localhost"
					],
					"port": "8051",
					"path": [
						"customers",
						"all"
					]
				}
			},
			"response": []
		},
		{
			"name": "CUSTOMER BY ID",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8051/customers/2",
					"host": [
						"localhost"
					],
					"port": "8051",
					"path": [
						"customers",
						"2"
					]
				}
			},
			"response": []
		},
		{
			"name": "CREATE CUSTOMER",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"name\": \"usuario Uno\",\r\n    \"state\": \"ACTIVE\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8051/customers",
					"host": [
						"localhost"
					],
					"port": "8051",
					"path": [
						"customers"
					]
				}
			},
			"response": []
		},
		{
			"name": "UPDATE CUSTOMER BY ID",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"name\": \"Usuario Dos\",\r\n    \"state\": \"INACTIVE\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8051/customers/1",
					"host": [
						"localhost"
					],
					"port": "8051",
					"path": [
						"customers",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "DELETE CUSTOMER BY ID",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "localhost:8051/customers/1",
					"host": [
						"localhost"
					],
					"port": "8051",
					"path": [
						"customers",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "COUNTRY ALL",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8052/countries/all",
					"host": [
						"localhost"
					],
					"port": "8052",
					"path": [
						"countries",
						"all"
					]
				}
			},
			"response": []
		},
		{
			"name": "COUNTRY BY ID",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8052/countries/1",
					"host": [
						"localhost"
					],
					"port": "8052",
					"path": [
						"countries",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "CREATE COUNTRY",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"country\": \"COLOMBIA\",\r\n    \"state\": \"ACTIVE\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8052/countries",
					"host": [
						"localhost"
					],
					"port": "8052",
					"path": [
						"countries"
					]
				}
			},
			"response": []
		},
		{
			"name": "UPDATE COUNTRY BY ID",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"country\": \"URUGUAY\",\r\n    \"state\": \"INACTIVE\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "localhost:8052/countries/1",
					"host": [
						"localhost"
					],
					"port": "8052",
					"path": [
						"countries",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "DELETE COUNTRY BY ID",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "localhost:8052/countries/2",
					"host": [
						"localhost"
					],
					"port": "8052",
					"path": [
						"countries",
						"2"
					]
				}
			},
			"response": []
		},
		{
			"name": "CUSTOMER SHOPPING BY ID",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8053/telefonica/v1/service-shopping/invoices/customers/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8053",
					"path": [
						"telefonica",
						"v1",
						"service-shopping",
						"invoices",
						"customers",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "COUNTRY SHOPPING BY ID",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8053/telefonica/v1/service-shopping/invoices/countries/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8053",
					"path": [
						"telefonica",
						"v1",
						"service-shopping",
						"invoices",
						"countries",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "SUBSCRIBER SHOPPING BY ID",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8053/telefonica/v1/service-shopping/invoices/subscribers/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8053",
					"path": [
						"telefonica",
						"v1",
						"service-shopping",
						"invoices",
						"subscribers",
						"1"
					]
				}
			},
			"response": []
		}
	]
}

```
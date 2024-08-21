# Bankdata Code Challenge

Run the project using IntelliJ

Access the database at: http://localhost:8080/h2-console

Use the following curl commands to access the api endpoints:

## Create new accounts
'''
curl -X POST http://localhost:8080/createAccount -H "Content-Type: application/json" -d "{\"accountNumber\": \"123e4567-e89b-12d3-a456-426614174000\", \"balance\": 100.00, \"firstName\": \"John\", \"lastName\": \"Doe\"}"
'''

'''
curl -X POST http://localhost:8080/createAccount -H "Content-Type: application/json" -d "{\"accountNumber\": \"123e4567-e89b-12d3-a456-426614174001\", \"balance\": 100.00, \"firstName\": \"Jane\", \"lastName\": \"Doe\"}"
'''

## Deposit more money
'''
curl -X POST http://localhost:8080/depositToAccount -H "Content-Type: application/json" -d "{\"accountNumber\": \"123e4567-e89b-12d3-a456-426614174000\", \"balance\": 50.00}"
'''

## Transfer some money
'''
curl -X POST http://localhost:8080/transferBetweenAccounts -H "Content-Type: application/json" -d "{\"fromAccountNumber\": \"123e4567-e89b-12d3-a456-426614174000\", \"toAccountNumber\": \"123e4567-e89b-12d3-a456-426614174001\",\"balance\": 100.00}"
'''

## Get account balance
'''
curl -X GET http://localhost:8080/accountBalance?accountNumber="123e4567-e89b-12d3-a456-426614174000"
'''

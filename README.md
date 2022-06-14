# Banking-Application
Banking Application with 2 Components i.e Customer And Account. Made with Java, SpringBoot, Hibernate, JPA.

Adding Different Calls with the type of body passed:

<b> 1.  GET : Get All Customers </b>

   curl --location --request GET 'localhost:8080/allCustomers'


<b>2. GET : Find Customer By Id</b>

  curl --location --request GET 'localhost:8080/customer/1'


<b>3. POST : Update Customer By Id</b>

  curl --location --request PUT 'localhost:8080/updateCustomer/1' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "email": "xyz@gmail.com ",
      "phoneNumber": "0107",
      "age": 33
  }'


<b> 4. DELETE : Delete Customer By ID</b>

  curl --location --request DELETE 'localhost:8080/deleteCustomer/1'
  
  
<b>5. GET : Get All Accounts that are present in System</b>

  curl --location --request GET 'localhost:8080/allAccounts'
  
  
<b>6. POST : Add Customer with Account</b>

  curl --location --request POST 'localhost:8080/addAccCustomer' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "email": "xyz@gmail.com ",
      "phoneNumber": "12345",
      "name": "Tushar",
      "age": 11,
      "account": [{
          "accountType": "SAVINGS",
         "balance": 100
      }]  
  }'
  
  
<b>7. GET : Get Account by Id</b>

  curl --location --request GET 'localhost:8080/accounts/1'


<b>8. GET : Get Balance By Account Id</b>

  curl --location --request GET 'localhost:8080/accounts/getBalance/1'



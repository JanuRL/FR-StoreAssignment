Implementation of JPA repository for use case of customer, product and order request.

Steps to run - 

1. Double click on the executable jar provided.
2. Wait for it to finish launching the app. Hit http://localhost:8081/index. Make sure no other process is running on port 8081.
3. Welcome page should be displayed. Click on the button to view list of products.
4. Click on Buy to update the customer's available points.

For now, only one product can be purchased at a time through UI. However, API supports multiple purchases in one go.

Requests - 
http://localhost:8081/index - For UI operations.

In case, one wishes to hit the APIs directly, use following requests - 

http://localhost:8081/product/findAll

http://localhost:8081/customer/findAll

http://localhost:8081/customer/requestOrder
{
  "custId":3,
  "productList":["mbl","ltp"]
}
Response:
Ok



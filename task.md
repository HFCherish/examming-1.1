#tasks
# /products
* post
	* return 201 when create product --10
	* try to save to db and can get that one from db;
	* contains url in location;

	* return 400 when name empty;
* get
	* return 200 when get all -- 10: 4
	* try to get from db and can get all details -- 10: 12


# /producta/pid
* get
	* return 200 when get product -- 2
	* contains right id, name, links in response;  --10 : 10

	* return 404 when not exists --5: 1

# /users
* post
	* return 201 when create user
	* try to save to db and can get that one from db;
	* contains url in location; --15 : 23

	* return 400 when name empty; --10 : 5


# /users/uid
* get
	* return 200 when get product
	* contains right details in response;  --10 : 15

	* return 404 when not exists --1: 1

# /users/uid/orders
* post
	* return 201 when create order
	* try to save to db and can get that one from db;
	* contains url in location; --15 : 3h

	* return 400 when incomplete input;  --5 : 9
	* return 400 when productId invalid; --10
* get
	* return 200 when get all -- 5
	* try to get from db and can get all details(without order_item) -- 10: 12


# /users/uid/orders/oid
* get
	* return 200 when get oid
	* contains right detail in response;  --15

	* return 404 when not exists --5

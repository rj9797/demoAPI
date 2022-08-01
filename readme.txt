* There are 4 APIs are implemented in the application(/storeurl,/get,/count,/list).
* Hit the /storeurl API in order to register the URL.
* Now hit /get API by passing the parameter in URL with key "url" , to get the unique short key of the registered URL
* Hit /count API by passing the parameter in URL with key "url" to get the number of usage counts by the user.
* Hit /list API by passing the parameter in URL with key "page" (to get the starting page) and "size"(number of records from starting page) to get all the list of URLs and count in json form.
* Any error of information will be printed in logs folder inside the root folder.
* Unit testing is implemented to test all 4 APIs simultaneously.

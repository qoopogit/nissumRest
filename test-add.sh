curl -v  --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"Alberto Garcia","password":"Abeto123@","email":"beto.garcia.dk@gmail.com", "phones":[{"number":"1234567","cityCode":"02","countryCode":"+593"},{"number":"7654321","cityCode":"06","countryCode":"+593"} ] }' \
  http://localhost:8080/users/add

exit
curl -v  --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"Pablo Lopez","password":"beto123","email":"pablo.lopez.dk@gmail.com"}' \
  http://localhost:8080/users/add



curl -v  --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"Carlos Duarte","password":"beto123","email":"carlos.duarte@gmail.com"}' \
  http://localhost:8080/users/add


curl -v  --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"Juan Rodriguez","password":"beto123","email":"juan.rodriguez@gmail.com"}' \
  http://localhost:8080/users/add

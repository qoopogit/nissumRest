
echo -e "\n\nError por no cumplir con politica password\n"
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"Alberto Garcia","password":"Abetoabeto","email":"beto.garcia.dk@gmail.com", "phones":[{"number":"1234567","cityCode":"02","countryCode":"+593"},{"number":"7654321","cityCode":"06","countryCode":"+593"} ] }' \
  http://localhost:8080/users/add

echo -e "\n\nError por no enviar email\n"
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"beto","password":"beto123"}' \
  http://localhost:8080/users/add

echo -e "\n\nError por email invalido\n"
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"beto", "email":"@gmail.com","password":"beto123"}' \
  http://localhost:8080/users/add

echo -e "\n\nError por email invalido\n"
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"beto", "email":"cualquiercontenido","password":"beto123"}' \
  http://localhost:8080/users/add


echo -e "\n\nError por email duplicado\n"
curl  --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"beto","password":"beto123","email":"beto.garcia.dk@gmail.com"}' \
  http://localhost:8080/users/add

